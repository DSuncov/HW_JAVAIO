import java.io.File;

public class Main {
    public static void main(String[] args) {
        File dir1 = new File("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\src");
        File dir2 = new File("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\res");
        File dir3 = new File("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames");
        File dir4 = new File("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\temp");
        File dir5 = new File(dir1, "main");
        File dir6 = new File(dir1, "test");
        File dir7 = new File(dir2, "drawables");
        File dir8 = new File(dir2, "vectors");
        File dir9 = new File(dir2, "icons");

        Manager manager = new Manager();

        manager.createDir(dir1, "src");
        manager.createDir(dir2, "res");
        manager.createDir(dir3, "savegames");
        manager.createDir(dir4, "temp");
        manager.createDir(dir5, "main");
        manager.createDir(dir6, "test");
        manager.createDir(dir7, "drawables");
        manager.createDir(dir8, "vectors");
        manager.createDir(dir9, "icons");

        //Добавляем файлы в директорию main
        File file1 = new File(dir5, "Main.java");
        File file2 = new File(dir5, "Utils.java");
        File file3 = new File(dir4, "temp.txt");

        manager.createFile(file1, "Main.java");
        manager.createFile(file2, "Utils.java");
        manager.createFile(file3, "temp.txt");

        manager.writeToTxtFile("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\temp\\temp.txt", false);

        GameProgress saveGame1 = new GameProgress(100, 5, 3, 10);
        GameProgress saveGame2 = new GameProgress(85, 10, 15, 5);
        GameProgress saveGame3 = new GameProgress(50, 12, 10, 7);

        String path1 = "C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save1.dat";
        String path2 = "C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save2.dat";
        String path3 = "C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save3.dat";

        manager.saveGame(path1, saveGame1);
        manager.saveGame(path2, saveGame2);
        manager.saveGame(path3, saveGame3);

        String[] paths = new String[3];
        paths[0] = path1;
        paths[1] = path2;
        paths[2] = path3;

        String[] zipfiles = new String[3];
        zipfiles[0] = "packed_save1.dat";
        zipfiles[1] = "packed_save2.dat";
        zipfiles[2] = "packed_save3.dat";

        manager.zipFiles("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\saves.zip", paths, zipfiles);


        manager.deleteFiles("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save1.dat");
        manager.deleteFiles("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save2.dat");
        manager.deleteFiles("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\save3.dat");


        manager.openZip("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\saves.zip", dir3);

        manager.openProgess("C:\\Users\\Дмитрий\\IdeaProjects\\Games\\savegames\\packed_save1.dat");
    }
}

