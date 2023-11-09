
package grafikus.foci.model;

import javax.persistence.*;
@Entity
@Table(name = "klub")
public class ClubModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int Id;
    @Column(name = "csapatnev")
    public String ClubName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public ClubModel(int id, String clubName) {
        Id = id;
        ClubName = clubName;
    }

    public ClubModel() {

    }
}