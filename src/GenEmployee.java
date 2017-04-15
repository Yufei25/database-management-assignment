import java.io.*;

/**
 * Created by Lockdown on 14/04/2017.
 */
public class GenEmployee {
    private static int[] empnos = {111, 211, 311, 411, 121, 221, 323, 425, 131, 132,
            133, 231, 234, 333, 334, 431, 432, 141, 142, 243, 342, 441};

    public void read() throws IOException {
        InputStream in = new FileInputStream(new File("Employee.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null && count < empnos.length) {
            Employee e = new Employee(line, empnos[count++]);
            System.out.println(e.toInsert());
        }
        reader.close();
    }

    public static void main(String[] args) throws Exception {
        new GenEmployee().read();
    }
}

class Employee {
    private int EmpNo;
    private String FName;
    private String LName;
    private String Position;
    private String Phone;
    private String Email;
    private String DOB;
    private String Gender;
    private int Salary;
    private String HireDate;
    private int OutNo;
    private int SupervisorNo;

    public Employee(String line, int empNo) {
        /**
         * employee number:
         * 3 digits: abc
         * a: outlet number
         * b: position (from 1 to 4, if 1 => supervisor)
         * c: sequence number
         */
        String[] parts = line.trim().split(",");
        this.EmpNo = empNo;
        switch ((this.EmpNo % 100) / 10) {
            case 1:
                this.Position = "Manager";
                break;
            case 2:
                this.Position = "Accounting Analyst";
                break;
            case 3:
                this.Position = "Promoter";
                break;
            default:
                this.Position = "Repairman";
                break;
        }
        this.FName = parts[1];
        this.LName = parts[2];
        this.Phone = parts[4];
        this.Email = makeEmail(parts[5]);
        this.DOB = parts[6];
        this.Gender = parts[7];
        this.Salary = ((int) (Integer.parseInt(parts[8]) / 1000)) * 1000;
        this.HireDate = parts[9];
        this.OutNo = (this.EmpNo / 100) * 100;
        this.SupervisorNo = (this.EmpNo - (this.EmpNo % 100)) + 11;
    }

    public static String makeEmail(String email) {
        String head = email.split("@")[0];
        return head + "@geekrent.com";
    }

    public String toInsert() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Insert into EMPLOYEE values (");
        builder.append(this.EmpNo + ", '");
        builder.append(this.FName + "', '");
        builder.append(this.LName + "', '");
        builder.append(this.Position + "', '");
        builder.append(this.Phone + "', '");
        builder.append(this.Email + "', '");
        builder.append(this.DOB + "', '");
        builder.append(this.Gender + "', ");
        builder.append(this.Salary + ", '");
        builder.append(this.HireDate + "', ");
        builder.append(this.OutNo + ", ");
        if ((this.EmpNo % 100) / 10 == 1) {
            builder.append("NULL);");
        } else {
            builder.append(this.SupervisorNo + ");");
        }
        return builder.toString();
    }
}
