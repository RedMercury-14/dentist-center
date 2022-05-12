package by.mytest.main.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3811663678432725890L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idorder")
	private int idOrder;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_Login")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "idschedule")
	private Schedule schedule;
	
	@Column(name="comment_order")
	private String commentOrder;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getCommentOrder() {
		return commentOrder;
	}

	public void setCommentOrder(String commentOrder) {
		this.commentOrder = commentOrder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return idOrder == other.idOrder;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", user=" + user + ", schedule=" + schedule + ", commentOrder="
				+ commentOrder + "]";
	}
	
	
}
