package pl.javastart.equipy.assignments;

import java.time.LocalDateTime;

public class AssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private Long userId;

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

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

   public static class AssignmentDtoBuilder {

        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private Long assetId;
        private Long userId;

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

       public AssignmentDtoBuilder assetId(Long assetId) {
           this.assetId = assetId;
           return this;
       }

        public AssignmentDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public AssignmentDto build() {
            AssignmentDto assignmentPerUserDto = new AssignmentDto();
            assignmentPerUserDto.setId(this.id);
            assignmentPerUserDto.setStart(this.start);
            assignmentPerUserDto.setEnd(this.end);
            assignmentPerUserDto.setAssetId(this.assetId);
            assignmentPerUserDto.setUserId(this.userId);
            return assignmentPerUserDto;
        }
    }

    public static AssignmentDtoBuilder builder() {
        return new AssignmentDtoBuilder();
    }
}
