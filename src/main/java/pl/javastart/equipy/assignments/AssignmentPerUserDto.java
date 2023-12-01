package pl.javastart.equipy.assignments;

import pl.javastart.equipy.users.User;

import java.time.LocalDateTime;

public class AssignmentPerUserDto {
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

    public static class AssignmentPerUserDtoBuilder {

        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private Long userId;
        private Long assetId;
        private String assetName;
        private String assetSerialNumber;

        public AssignmentPerUserDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentPerUserDtoBuilder start(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentPerUserDtoBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public AssignmentPerUserDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public AssignmentPerUserDtoBuilder assetId(Long assetId) {
            this.assetId = assetId;
            return this;
        }

        public AssignmentPerUserDtoBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public AssignmentPerUserDtoBuilder assetSerialNumber(String assetSerialNumber) {
            this.assetSerialNumber = assetSerialNumber;
            return this;
        }

        public AssignmentPerUserDto build() {
            AssignmentPerUserDto assignmentPerUserDto = new AssignmentPerUserDto();
            assignmentPerUserDto.setId(this.id);
            assignmentPerUserDto.setStart(this.start);
            assignmentPerUserDto.setEnd(this.end);
            assignmentPerUserDto.setUserId(this.userId);
            assignmentPerUserDto.setAssetId(this.assetId);
            assignmentPerUserDto.setAssetName(this.assetName);
            assignmentPerUserDto.setAssetSerialNumber(this.assetSerialNumber);
            return assignmentPerUserDto;
        }
    }

    public static AssignmentPerUserDtoBuilder builder() {
        return new AssignmentPerUserDtoBuilder();
    }
}
