import java.io.*;

/**
 * Created by Lockdown on 14/04/2017.
 */
public class GenRechargement {
    public void read() throws IOException {
        InputStream in = new FileInputStream(new File("rechargement.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println(new Rechargement(line).toInsert());
        }
        reader.close();
    }


    public static void main(String[] args) throws IOException {
        new GenRechargement().read();
    }
}

/**
 * CREATE TABLE RECHARGEMENT (
 * RentalNo      CHAR(6) NOT NULL ,
 * StartDate     DATE,
 * ReturnDate    DATE,
 * MileageBefore NUMBER(7),
 * NileageAfter  NUMBER(7),
 * InsuranceType VARCHAR2(8),
 * ClientNo      CHAR(6),
 * LicenseNo     CHAR(6),
 * CONSTRAINT Rechargement_PK PRIMARY KEY (RentalNo)
 * );
 */
class Rechargement {
    private String RentalNo;
    private String StartDate;
    private String ReturnDate;
    private int MileageBefore;
    private int MileageAfter;
    private String InsuranceType;
    private String ClientNo;
    private String LicenseNo;

    public Rechargement(String line) {
        String[] parts = line.trim().split(",");
        this.RentalNo = "R" + (1000 + Integer.parseInt(parts[0]));
        this.StartDate = parts[1];
        this.ReturnDate = parts[2];
        this.MileageBefore = Integer.parseInt(parts[3]);
        this.MileageAfter = Integer.parseInt(parts[3]) + Integer.parseInt(parts[4]);
        switch (Integer.parseInt(parts[5])) {
            case 1:
                this.InsuranceType = "Comprehensive";
                break;
            case 2:
                this.InsuranceType =
                        "Third Party Property Damage";
                break;
            default:
                this.InsuranceType = "Third Party Fire & Theft";
                break;
        }
        this.ClientNo = "c" + (3000 + Integer.parseInt(parts[6]));
        this.LicenseNo = "L" + (1000 + Integer.parseInt(parts[7]));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Insert into RECHARGEMENT values ('" + this.RentalNo + "', '");
        builder.append(this.StartDate + "', '");
        builder.append(this.ReturnDate + "', ");
        builder.append(this.MileageBefore + ", ");
        builder.append(this.MileageAfter + ", '");
        builder.append(this.InsuranceType + "', '");
        builder.append(this.ClientNo + "', '");
        builder.append(this.LicenseNo + "');");
        return builder.toString();
    }

    public String toInsert() {
        return this.toString();
    }
}