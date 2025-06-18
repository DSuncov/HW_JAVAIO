import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Manager {

    StringBuilder sb = new StringBuilder();

    public void createDir(File dir, String nameDir) {
        if (dir.mkdir()) {
            sb.append("Создана директория ").append(nameDir).append('\n');
        }
    }

    public void createFile(File file, String nameFile) {
        try {
            if (file.createNewFile()) {
                sb.append("Создан файл ").append(nameFile).append('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToTxtFile(String nameFile, Boolean append) {
        try (FileWriter writer = new FileWriter(nameFile, append)) {
            writer.write(String.valueOf(sb));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveGame (String path, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(save);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void zipFiles(String path, String[] paths, String[] zipfiles) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String pathfile : paths) {
                for (String file : zipfiles) {
                    try (FileInputStream fis = new FileInputStream(pathfile)) {
                        ZipEntry entry = new ZipEntry(file);
                        zout.putNextEntry(entry);
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        zout.write(buffer);
                        zout.closeEntry();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFiles(String pathFile) {
        Path path = Paths.get(pathFile);
        try {
            Files.delete(path);
            System.out.println("Файл удалён успешно");
        } catch (IOException e) {
            System.out.println("Не удалось удалить файл: " + e.getMessage());
        }
    }

    public void openZip(String pathFile, File dir) {

        File f = dir;
        try (ZipInputStream zins = new ZipInputStream(new FileInputStream(pathFile))) {
            ZipEntry entry;

            while((entry = zins.getNextEntry()) != null) {
                File newFile = new File(f + File.separator + entry.getName());

                try (FileOutputStream fout = new FileOutputStream(newFile)) {
                    for (int c = zins.read(); c != -1; c = zins.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    zins.closeEntry();
                }
            }

            zins.getNextEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public GameProgress openProgess(String pathFile) {
        GameProgress game = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathFile))) {
            game = (GameProgress) ois.readObject();
            System.out.println(game);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return game;
    }
}
