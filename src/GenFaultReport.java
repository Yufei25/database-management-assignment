import java.io.*;

/**
 * Created by Lockdown on 15/04/2017.
 */
public class GenFaultReport {
    public void read() throws IOException {
        InputStream in = new FileInputStream(new File("faultreport.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println(new FaultReport(line).toInsert());
        }
        reader.close();
    }


    public static void main(String[] args) throws IOException {
        new GenFaultReport().read();
    }
}

/**
 * CREATE TABLE FAULTREPORT (
 * ReportNum   CHAR(6) NOT NULL ,
 * DateChecked DATE,
 * Comments    VARCHAR2(320),
 * EmpNo       CHAR(4),
 * LicenseNo   CHAR(6),
 * RentalNo    CHAR(6),
 * CONSTRAINT Faultreport_PK PRIMARY KEY (ReportNum)
 * );
 */
class FaultReport {
    private String ReportNum;
    private String DateChecked;
    private String Comments;
    private int EmpNo;
    private String LicenseNo;
    private String RentalNo;

    public FaultReport(String line) {
        String[] parts = line.trim().split(",");
        this.ReportNum = "rp" + (1000 + Integer.parseInt(parts[0]));
        this.DateChecked = parts[1];
        this.Comments = "...";
        this.EmpNo = 000;
        this.LicenseNo = "L" + parts[2];
        this.RentalNo = "R" + parts[3];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Insert into FAULTREPORT values ('");
        builder.append(this.ReportNum + "', '");
        builder.append(this.DateChecked + "', '");
        builder.append(this.Comments + "', '");
        builder.append(this.EmpNo + "', '");
        builder.append(this.LicenseNo + "', '");
        builder.append(this.RentalNo + "');");
        return builder.toString();
    }

    public String toInsert() {
        return this.toString();
    }
}

/*
Insert into FAULTREPORT values ('rp1001', '11-Jan-2017', '...', '0', 'L1', 'R1');
Insert into FAULTREPORT values ('rp1002', '12-Apr-2017', '...', '0', 'L2', 'R2');
Insert into FAULTREPORT values ('rp1003', '13-Jan-2017', '...', '0', 'L3', 'R3');
Insert into FAULTREPORT values ('rp1004', '10-Feb-2017', '...', '0', 'L4', 'R4');
Insert into FAULTREPORT values ('rp1005', '15-Jan-2017', '...', '0', 'L5', 'R5');
Insert into FAULTREPORT values ('rp1006', '15-Mar-2017', '...', '0', 'L6', 'R6');
Insert into FAULTREPORT values ('rp1007', '22-Jan-2017', '...', '0', 'L7', 'R7');
Insert into FAULTREPORT values ('rp1008', '08-Apr-2017', '...', '0', 'L8', 'R8');
Insert into FAULTREPORT values ('rp1009', '16-Jan-2017', '...', '0', 'L9', 'R9');
Insert into FAULTREPORT values ('rp1010', '28-Jan-2017', '...', '0', 'L10', 'R10');
Insert into FAULTREPORT values ('rp1011', '03-Jan-2017', '...', '0', 'L11', 'R11');
Insert into FAULTREPORT values ('rp1012', '12-Mar-2017', '...', '0', 'L12', 'R12');
Insert into FAULTREPORT values ('rp1013', '07-Jan-2017', '...', '0', 'L13', 'R13');
Insert into FAULTREPORT values ('rp1014', '23-Jan-2017', '...', '0', 'L14', 'R14');
Insert into FAULTREPORT values ('rp1015', '11-Mar-2017', '...', '0', 'L15', 'R15');
Insert into FAULTREPORT values ('rp1016', '22-Jan-2017', '...', '0', 'L16', 'R16');
Insert into FAULTREPORT values ('rp1017', '23-Feb-2017', '...', '0', 'L17', 'R17');
Insert into FAULTREPORT values ('rp1018', '03-Apr-2017', '...', '0', 'L18', 'R18');
Insert into FAULTREPORT values ('rp1019', '12-Apr-2017', '...', '0', 'L19', 'R19');
Insert into FAULTREPORT values ('rp1020', '08-Jan-2017', '...', '0', 'L20', 'R20');
Insert into FAULTREPORT values ('rp1021', '16-Feb-2017', '...', '0', 'L21', 'R21');
Insert into FAULTREPORT values ('rp1022', '02-Feb-2017', '...', '0', 'L22', 'R22');
Insert into FAULTREPORT values ('rp1023', '01-Jan-2017', '...', '0', 'L23', 'R23');
Insert into FAULTREPORT values ('rp1024', '05-Apr-2017', '...', '0', 'L24', 'R24');
Insert into FAULTREPORT values ('rp1025', '22-Feb-2017', '...', '0', 'L25', 'R25');
Insert into FAULTREPORT values ('rp1026', '06-Jan-2017', '...', '0', 'L26', 'R26');
Insert into FAULTREPORT values ('rp1027', '12-Apr-2017', '...', '0', 'L27', 'R27');
Insert into FAULTREPORT values ('rp1028', '27-Mar-2017', '...', '0', 'L28', 'R28');
Insert into FAULTREPORT values ('rp1029', '10-Apr-2017', '...', '0', 'L29', 'R29');
Insert into FAULTREPORT values ('rp1030', '20-Feb-2017', '...', '0', 'L30', 'R30');

 */