package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Manager extends Person {
  public static final String ID = "store.Server";
  public Manager(String name, String phone, String ssn) {
      super(name, phone);
      this.ssn = ssn;
  }
  public Manager(BufferedReader in) throws IOException {
      super(in);
      this.ssn  = in.readLine();
  }
  @Override
  public void save(BufferedWriter out) throws IOException {
      out.write(ID + '\n');
      super.save(out);
      out.write("" + ssn  + '\n');
  }
  @Override
  public String toString() {
      return "Manager " + name + " (" + phone + ", SSN is " + ssn + ")";
  }
  String ssn;
}
