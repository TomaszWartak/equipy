package pl.javastart.equipy.assignments;

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
    /*  todo  private Long userId;
    private Long assetId;*/
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    // todo private String assetName;
    // todo private String assetSerialNumber;

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
/* todo

    public Long getUserId() {
        return this.user.getId();
    }

    public void setUserId(Long userId) {
        this.user.setId(userId);
    }

    public Long getAssetId() {
        return this.asset.getId();
    }

    public void setAssetId(Long assetId) {
        this.asset.setId(assetId);
    }

    public String getAssetName() {
        return this.asset.getName();
    }

    public void setAssetName(String assetName) {
        this.asset.setName( assetName );
    }

    public String getAssetSerialNumber() {
        return asset.getSerialNumber();
    }

    public void setAssetSerialNumber(String assetSerialNumber) {
        asset.setSerialNumber( assetSerialNumber );
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id)/* todo &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(getAssetId(), that.getAssetId()) &&
                Objects.equals(getUserId(), that.getUserId())*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end/* todo, getAssetId(), getUserId()*/ );
    }

    public static class AssignmentBuilder {

        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private User user;
        private Asset asset;
/* todo        private Long userId;
        private Long assetId;
        private String assetName;
        private String assetSerialNumber;*/

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


/* todo
        public AssignmentBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public AssignmentBuilder assetId(Long assetId) {
            this.assetId = assetId;
            return this;
        }

        public AssignmentBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public AssignmentBuilder assetSerialNumber(String assetSerialNumber) {
            this.assetSerialNumber = assetSerialNumber;
            return this;
        }*/

        public Assignment build() {
            Assignment assignment = new Assignment();
            assignment.setId(this.id);
            assignment.setStart(this.start);
            assignment.setEnd(this.end);
            assignment.setUser( this.user );
            assignment.setAsset( this.asset );
            /* todo assignment.setUserId(this.userId);
            assignment.setAssetId(this.assetId);
            assignment.setAssetName(this.assetName);
            assignment.setAssetSerialNumber(this.assetSerialNumber);*/
            return assignment;
        }
    }

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

}
