import java.io.*;

/**
 * Created by Lockdown on 14/04/2017.
 */
public class GenVehicle {
    public void read() throws IOException {
        InputStream in = new FileInputStream(new File("vehicle.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println(new Vehicle(line));
        }
        reader.close();
    }

    public static void main(String... args) throws IOException {
        new GenVehicle().read();
    }
}

/**
 * CREATE TABLE VEHICLE (
 * LicenseNo      CHAR(6) NOT NULL ,
 * Make           VARCHAR2(16),
 * Model          VARCHAR2(16),
 * Color          VARCHAR2(16),
 * Year           NUMBER(4),
 * NoDoors        NUMBER(1),
 * Capacity       NUMBER(2),
 * DailyRate      NUMBER(4),
 * InspectionDate DATE,
 * OutNo          CHAR(4),
 * CONSTRAINT Vehicle_PK PRIMARY KEY (LicenseNo),
 * CONSTRAINT Color_enum CHECK (Color in ('BLACK', 'WHITE', 'BLUE', 'SILVER GRAY'))
 * );
 */

class Vehicle {
    private String LicenseNo;  //   CHAR(6) NOT NULL ,
    private String Make;   // VARCHAR2(16),
    private String Model; //  VARCHAR2(16),
    private String Color;//         VARCHAR2(16),
    private String Year;//      NUMBER(4),
    private int NoDoors;//    NUMBER(1),
    private int Capacity;//    NUMBER(2),
    private String DailyRate;//  NUMBER(4),
    private String InspectionDate;//DATE,
    private String OutNo;//CHAR(4),

    public Vehicle(String line) {
        String[] parts = line.trim().split(",");
        this.LicenseNo = parts[0];
        switch (Integer.parseInt(parts[1])) {
            case 1:
                this.Make = "A";
                break;
            case 2:
                this.Make = "B";
                break;
            case 3:
                this.Make = "C";
                break;
            default:
                this.Make = "D";
                break;
        }
        this.Model = parts[2];
        switch (Integer.parseInt(parts[3])) {
            case 1:
                this.Color = "BLACK";
                break;
            case 2:
                this.Color = "SILVER GRAY";
                break;
            case 3:
                this.Color = "WHITE";
                break;
            default:
                this.Color = "BLUE";
                break;
        }
        this.Year = parts[4];
        this.NoDoors = Integer.parseInt(parts[5]) * 2;
        this.Capacity = this.NoDoors + ((int) Math.random() * 2 - 1);
        this.DailyRate = parts[6];
        this.InspectionDate = parts[7];
        this.OutNo = parts[8];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Insert into VEHICLE values ('");
        builder.append(this.LicenseNo + "', '");
        builder.append(this.Make + "', '");
        builder.append(this.Model + "', '");
        builder.append(this.Color + "', ");
        builder.append(this.Year + ", ");
        builder.append(this.NoDoors + ", ");
        builder.append(this.Capacity + ", ");
        builder.append(this.DailyRate + ", '");
        builder.append(this.InspectionDate + "', '");
        builder.append(this.OutNo + "');");
        return builder.toString();
    }

    public String toInsert() {
        return this.toString();
    }
}