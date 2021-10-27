package application.model.entities;


import application.model.ResponseData;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "users")
public class User implements ResponseData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name",length = 50)
    private String lastName;

    @Column(name = "user_name",length = 50, unique = true)
    private String username;


    @Column(name = "email", length = 50,unique = true)
    private String email;


    @Column(name = "password",length = 100)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private List<String> role;

    @OneToMany(mappedBy = "user")
    private Set<Rating> ratings;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_flag_movies",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    Set<Movie> flaggedMovies = new HashSet<>();


    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String password, List<String> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void addFlaggedMovie(Movie movie){
        flaggedMovies.add(movie);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public List<String> getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
