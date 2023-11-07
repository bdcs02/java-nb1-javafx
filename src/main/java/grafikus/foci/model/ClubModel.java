<<<<<<< HEAD
package grafikus.foci.model;

import javax.persistence.*;
@Entity
@Table(name = "klub")
public class ClubModel {
    @Id @GeneratedValue
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
=======
package grafikus.foci.model;

import javax.persistence.*;
@Entity
@Table(name = "klub")
public class ClubModel {
    @Id @GeneratedValue
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
>>>>>>> 1b60a021d8ee0a46b6f9803e6cf6b66e7314ef1b
}