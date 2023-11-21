package pl.javastart.equipy.assignments;

import java.time.LocalDateTime;

public class AssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        private LocalDateTime start;
        private LocalDateTime end;
        private Long userId;
        private Long assetId;
        private String assetName;
        private String assetSerialNumber;

        public AssignmentDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentDtoBuilder start(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentDtoBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public AssignmentDtoBuilder userId(Long userId) {
            this.assetId = userId;
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
            assignmentDto.setUserId(this.userId);
            assignmentDto.setAssetId(this.assetId);
            assignmentDto.setAssetName(this.assetName);
            assignmentDto.setAssetSerialNumber(this.assetSerialNumber);
            return assignmentDto;
        }
    }
}
