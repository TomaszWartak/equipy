package pl.javastart.equipy.rentals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private Long assetId;
    private String assetName;
    private String assetSerialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetSerialNumber() {
        return assetSerialNumber;
    }

    public void setAssetSerialNumber(String assetSerialNumber) {
        this.assetSerialNumber = assetSerialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(assetId, that.assetId) && Objects.equals(assetName, that.assetName) && Objects.equals(assetSerialNumber, that.assetSerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, assetId, assetName, assetSerialNumber);
    }

    public class AssignmentBuilder {

        private Long id;
        private ZonedDateTime start;
        private ZonedDateTime end;
        private Long assetId;
        private String assetName;
        private String assetSerialNumber;

        public AssignmentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder withStart(ZonedDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentBuilder withEnd(ZonedDateTime end) {
            this.end = end;
            return this;
        }

        public AssignmentBuilder withAssetId(Long assetId) {
            this.assetId = assetId;
            return this;
        }

        public AssignmentBuilder withAssetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public AssignmentBuilder withAssetSerialNumber(String assetSerialNumber) {
            this.assetSerialNumber = assetSerialNumber;
            return this;
        }

        public Assignment build() {
            Assignment assignment = new Assignment();
            assignment.setId(this.id);
            assignment.setStart(this.start);
            assignment.setEnd(this.end);
            assignment.setAssetId(this.assetId);
            assignment.setAssetName(this.assetName);
            assignment.setAssetSerialNumber(this.assetSerialNumber);
            return assignment;
        }
    }
}
