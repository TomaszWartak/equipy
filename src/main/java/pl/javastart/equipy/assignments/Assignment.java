package pl.javastart.equipy.assignments;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import pl.javastart.equipy.assets.Asset;
import pl.javastart.equipy.users.User;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end );
    }

    public static class AssignmentBuilder {

        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private User user;
        private Asset asset;

        public AssignmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder start(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public AssignmentBuilder user(User user) {
            this.user = user;
            return this;
        }
        public AssignmentBuilder asset(Asset asset) {
            this.asset = asset;
            return this;
        }

        public Assignment build() {
            Assignment assignment = new Assignment();
            assignment.setId(this.id);
            assignment.setStart(this.start);
            assignment.setEnd(this.end);
            assignment.setUser( this.user );
            assignment.setAsset( this.asset );
            return assignment;
        }
    }

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

}
