import java.io.Serializable;

/**
 * Created by Mark Hadley on 4/30/2017.
 */
public class CloseTransaction implements Transaction, Serializable {
    Status status;
	int id;
    public CloseTransaction(){
        status = Status.FAILURE;
		id = 0;
    }

    @Override
    public Action getAction() {
        return Action.CLOSE;
    }

    @Override
    public void setStatus(Status s) {
        status = s;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int getId() {
        return 0;
    }
}
