package application.data.structures;

import application.constants.Errors;
import application.data.structures.core.Graph;
import application.exceptions.CyclicGraphException;
import application.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;

public class EdgeList implements Graph {
    private final Map<Path, List<Path>> edges = new HashMap<Path, List<Path>>();

    /**
     * Получение всех родительских нод (пути) определенной ноды.
     * @param node Нода.
     * @return Список родитесльких нод.
     */
    private List<Path> getNodesParents(final Path node) {
        List<Path> nodes = new ArrayList<Path>();
        for (final Path keyNode : edges.keySet()) {
            if (edges.get(keyNode).contains(node)) {
                nodes.add(keyNode);
            }
        }
        return nodes;
    }

    @Override
    public boolean hasCyclicDependency(final Path edge) {
        final List<Path> parentNodes = getNodesParents(edge);

        for (final Path parentNode : parentNodes) {
            if (edges.containsKey(edge) && edges.get(edge).contains(parentNode)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addNode(final Path filePath) {
        edges.putIfAbsent(filePath, new ArrayList<Path>());
    }

    @Override
    public void addEdge(final Path node, final Path edge) throws CyclicGraphException, NoSuchElementException, IllegalArgumentException {
        if (!edges.containsKey(node)) {
            throw new NoSuchElementException(Errors.NODE_NOT_IN_GRAPH);
        }

        if (!FileUtils.isValidFile(edge)) {
            throw new IllegalArgumentException(Errors.FILE_IS_DIR_OR_NOT_FOUND);
        }

        edges.get(node).add(edge);

        if (hasCyclicDependency(edge) || edge.equals(node)) {
            throw new CyclicGraphException(Errors.CYCLIC_GRAPH);
        }
    }

    private Map<Path, List<Path>> deepCopy() {
        Map<Path, List<Path>> copy = new HashMap<Path, List<Path>>();

        for (final Path key : edges.keySet()) {
            List<Path> nodes = new ArrayList<Path>(edges.get(key));
            copy.put(key, nodes);
        }

        return copy;
    }

    @Override
    public List<Path> sortedList() {
        // Топологическая сортировка с использование алгоритма Кана.

        Map<Path, List<Path>> copy = deepCopy();
        List<Path> sortedList = new ArrayList<Path>();

        for (final Path key : edges.keySet()) {
            sortedList.add(key);
            sortedList.addAll(copy.get(key));
        }

        // если файл А, зависит от файла В, то файл А находится ниже файла В в списке.
        Collections.reverse(sortedList);

        return sortedList;
    }
}
