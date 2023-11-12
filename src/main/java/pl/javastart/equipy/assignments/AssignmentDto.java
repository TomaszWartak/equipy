package pl.javastart.equipy.assignments;

import java.time.ZonedDateTime;

public class AssignmentDto {
    private Long id;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private Long assetId;
    private String assetName;
    private String assetSerialNumber;

    // Gettery i settery

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

    public class AssignmentDtoBuilder {

        private Long id;
        private ZonedDateTime start;
        private ZonedDateTime end;
        private Long assetId;
        private String assetName;
        private String assetSerialNumber;

        public AssignmentDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentDtoBuilder start(ZonedDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentDtoBuilder end(ZonedDateTime end) {
            this.end = end;
            return this;
        }

        public AssignmentDtoBuilder assetId(Long assetId) {
            this.assetId = assetId;
            return this;
        }

        public AssignmentDtoBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public AssignmentDtoBuilder assetSerialNumber(String assetSerialNumber) {
            this.assetSerialNumber = assetSerialNumber;
            return this;
        }

        public AssignmentDto build() {
            AssignmentDto assignmentDto = new AssignmentDto();
            assignmentDto.setId(this.id);
            assignmentDto.setStart(this.start);
            assignmentDto.setEnd(this.end);
            assignmentDto.setAssetId(this.assetId);
            assignmentDto.setAssetName(this.assetName);
            assignmentDto.setAssetSerialNumber(this.assetSerialNumber);
            return assignmentDto;
        }
    }
}
