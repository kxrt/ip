import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
  private final String filefolder = "data";
  private final String filename = "duke.txt";
  private final String filepath = filefolder + File.separator + filename;

  public Storage() throws DukeException {
    this.createStorageFile();
  }

  private void createStorageFile() throws DukeException {
    try {
      File directory = new File(filefolder);
      if (!directory.exists()) {
        directory.mkdir();
      }
      File saved = new File(filepath);
      if (!saved.exists()) {
        saved.createNewFile();
      }
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    }
  }

  public ArrayList<DukeTask> load() throws DukeException {
    ArrayList<DukeTask> taskList = new ArrayList<>();
    try {
      File f = new File(filepath);
      Scanner s = new Scanner(f);
      while (s.hasNext()) {
        String[] taskSplit = s.nextLine().split(" >> ");
        switch (taskSplit[0]) {
          case "T": {
            ToDo task = new ToDo(taskSplit[2]);
            if (taskSplit[1].equals("1")) {
              task.markAsDone();
            }
            taskList.add(task);
            break;
          }
          case "D": {
            Deadline task = new Deadline(taskSplit[2], taskSplit[3]);
            if (taskSplit[1].equals("1")) {
              task.markAsDone();
            }
            taskList.add(task);
            break;
          }
          case "E": {
            Event task = new Event(taskSplit[2], taskSplit[3]);
            if (taskSplit[1].equals("1")) {
              task.markAsDone();
            }
            taskList.add(task);
            break;
          }
        }
      }
    } catch (FileNotFoundException e) {
      throw new DukeException(e.getMessage());
    }
    return taskList;
  }

  public void save(ArrayList<DukeTask> itemList) {
    try {
      FileWriter fw = new FileWriter(filepath);
      for (DukeTask t : itemList) {
        fw.write(t.getStorageString() + "\n");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
