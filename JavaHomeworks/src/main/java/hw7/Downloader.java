package hw7;

import hw7.constants.Errors;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Downloader implements Runnable {
    private final String[] urls;

    private final File path;

    public Downloader(final String path, final String... urls) {
        this.path = new File(path);
        if (!this.path.exists()) {
            if(!this.path.mkdirs()) {
                System.out.println(Errors.MKDIR_FAIL);
            }
        }

        this.urls = urls;
    }

    private String getFileName(final URL url) {
        String[] names = url.getFile().split("/");

        return names[names.length - 1];
    }

    private void download(final String... urls) {
        for (final String url : urls) {
            try {
                URL urlObject = new URL(url);
                File file = new File(path, getFileName(urlObject));

                if (!file.createNewFile()) {
                    System.out.println(Errors.FILE_OPEN_FAIL);
                    return;
                }

                BufferedInputStream in = new BufferedInputStream(urlObject.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }

                in.close();
                fileOutputStream.close();
            } catch (final IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void run() {
        download(urls);
    }
}
