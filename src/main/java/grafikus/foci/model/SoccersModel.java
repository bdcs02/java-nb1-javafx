package grafikus.foci.model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.persistence.*;

import javax.persistence.Column;

@Entity
@Table(name = "labdarugo")
public class SoccersModel {
    @Id @GeneratedValue
    @Column(name = "id")
    public int id;

    @Column(name = "mezszam")
    public int playerNumber;

    @Column(name = "klubid")
    public int clubId;

    @Column(name = "posztid")
    public int playerPost;

    @Column(name = "utonev")
    public String firstName;

    @Column(name = "vezeteknev")
    public String lastName;

    @Column(name = "szulido")
    public String birthDate;

    @Column(name = "magyar")
    public String isHungarian;

    @Column(name = "kulfoldi")
    public String isForeign;

    @Column(name = "ertek")
    public String playerRate;

    public  SoccersModel() {

    }


    public SoccersModel(int playerNumber, int clubId, int playerPost, String firstName, String lastName, String birthDate, String isHungarian, String isForeign, String playerRate) {
        this.playerNumber = playerNumber;
        this.clubId = clubId;
        this.playerPost = playerPost;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isHungarian = isHungarian;
        this.isForeign = isForeign;
        this.playerRate = playerRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getPlayerPost() {
        return playerPost;
    }

    public void setPlayerPost(int playerPost) {
        this.playerPost = playerPost;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIsHungarian() {
        return isHungarian;
    }

    public void setIsHungarian(String isHungarian) {
        this.isHungarian = isHungarian;
    }

    public String getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(String isForeign) {
        this.isForeign = isForeign;
    }

    public String getPlayerRate() {
        return playerRate;
    }

    public void setPlayerRate(String playerRate) {
        this.playerRate = playerRate;
    }
}
