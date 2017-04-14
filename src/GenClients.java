import java.io.*;

/**
 * Created by Lockdown on 14/04/2017.
 */
public class GenClients {
    public void read() throws IOException {
        InputStream in = new FileInputStream(new File("clients1.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println(new Client(line).toInsert());
        }
        reader.close();
    }


    public static void main(String[] args) throws IOException {
        new GenClients().read();
    }
}

class Client {
    /**
     * CREATE TABLE CLIENT (
     * ClientNo      CHAR(6) NOT NULL ,
     * ClientName    CHAR(20),
     * Street        VARCHAR2(32),
     * City          VARCHAR2(20),
     * State         VARCHAR2(20),
     * ZipCode       NUMBER(6),
     * WebAddress    VARCHAR2(64),
     * Contact_FName VARCHAR2(20),
     * Contact_LName VARCHAR2(20),
     * Phone         VARCHAR2(20),
     * Email         VARCHAR2(32),
     * CONSTRAINT Client_PK PRIMARY KEY (ClientNo)
     * );
     */
    private String ClientNo;
    private String ClientName;
    private String Street;
    private String City;
    private String State;
    private int zipcode;
    private String webaddress;
    private String Contact_FName;
    private String Contact_LName;
    private String Phone;
    private String Email;

    public Client(String line) {
        String[] parts = line.trim().split(",");
        this.ClientNo = "c" + (3000 + Integer.parseInt(parts[0]));
        this.Street = parts[1];
        this.City = parts[2];
        this.State = parts[3];
        this.Phone = parts[4];
        this.zipcode = Integer.parseInt(parts[5]);
        this.webaddress = parts[6];
        this.Contact_FName = parts[7];
        this.Contact_LName = parts[8];
        this.ClientName = this.Contact_FName + " " + this.Contact_LName;
        this.Email = parts[9];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Insert into CLIENT values ('");
        builder.append(this.ClientNo + "', '");
        builder.append(this.ClientName + "', '");
        builder.append(this.Street + "', '");
        builder.append(this.City + "', '");
        builder.append(this.State + "', ");
        builder.append(this.zipcode + ", '");
        builder.append("www." + this.webaddress.toLowerCase() + ".com', '");
        builder.append(this.Contact_FName + "', '");
        builder.append(this.Contact_LName + "', '");
        builder.append(this.Phone + "', '");
        builder.append(this.Email + "');");
        return builder.toString();
    }

    public String toInsert() {
        return this.toString();
    }
}
