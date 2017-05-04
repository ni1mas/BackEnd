package NiUnaMas.Models;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Robert on 04/05/2017.
 */
@Entity
@Table(name = "Answer")
public class Answers {
    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = true)
    private Poll poll;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;
    public Answers(String m){
        message = m;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
