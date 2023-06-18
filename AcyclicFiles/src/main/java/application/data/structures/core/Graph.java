package application.data.structures.core;

import application.exceptions.CyclicGraphException;

import java.nio.file.Path;
import java.util.List;

public interface Graph {
    /**
     * Проверка ноды на циклическую зависимость.
     * @param node Нода.
     * @return true, если есть циклическая зависимость, иначе false.
     */
    boolean hasCyclicDependency(final Path node);

    /**
     * Добавляет ноду в граф.
     * @param filePath Путь до файла(ноды).
     */
    void addNode(final Path filePath);

    /**
     * Добавляет направленное ребро от ноды fromNode к ноде toNode.
     * @param fromNode Нода от которой идет ребро.
     * @param toNode Нода к которой идет ребро.
     */
    void addEdge(final Path fromNode, final Path toNode) throws CyclicGraphException;

    /**
     * Сортиврока графа.
     * @return Отсортированный граф в виде списка.
     */
    List<Path> sortedList();
}
