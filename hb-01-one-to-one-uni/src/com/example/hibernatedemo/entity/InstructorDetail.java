package com.example.hibernatedemo.entity;

import javax.persistence.*;

// annotate class as an Entity and map DB table columns
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

	// define fields and annotate with table column names
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="youtube_channel")
	private String youtubeChannel;

	@Column(name="hobby")
	private String hobby;

	// create constructors
	public InstructorDetail() {}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	// generate getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	// generate toString() method for output debug

	@Override
	public String toString() {
		return "InstructorDetail{" +
				"id=" + id +
				", youtubeChannel='" + youtubeChannel + '\'' +
				", hobby='" + hobby + '\'' +
				'}';
	}
}
