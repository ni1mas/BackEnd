package NiUnaMas.Models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Robert on 04/05/2017.
 */
@Entity
@Table(name = "Poll")
public class Poll {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    // Notification's id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    @Column(name = "CREATED", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private ArrayList<String> answers;

    public Poll(){
        answers = new ArrayList<>();
    }
    public Poll(User u, ArrayList<String>a){
        user = u;
        answers = a;
    }

    public Poll(ArrayList<String>a){
        answers = a;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}
