package pl.javastart.equipy.assignments;

import java.time.LocalDateTime;

public class AssignmentPerAssetDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;

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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }


   public static class AssignmentPerAssetDtoBuilder {

        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private Long assetId;
        private Long userId;
        private String firstName;
        private String lastName;
        private String pesel;

        public AssignmentPerAssetDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentPerAssetDtoBuilder start(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public AssignmentPerAssetDtoBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

       public AssignmentPerAssetDtoBuilder assetId(Long assetId) {
           this.assetId = assetId;
           return this;
       }

        public AssignmentPerAssetDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public AssignmentPerAssetDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AssignmentPerAssetDtoBuilder lastName(String lastName) {
            this.lastName = lastName;;
            return this;
        }

        public AssignmentPerAssetDtoBuilder pesel(String pesel) {
            this.pesel = pesel;;
            return this;
        }

        public AssignmentPerAssetDto build() {
            AssignmentPerAssetDto assignmentPerUserDto = new AssignmentPerAssetDto();
            assignmentPerUserDto.setId(this.id);
            assignmentPerUserDto.setStart(this.start);
            assignmentPerUserDto.setEnd(this.end);
            assignmentPerUserDto.setAssetId(this.assetId);
            assignmentPerUserDto.setUserId(this.userId);
            assignmentPerUserDto.setFirstName(this.firstName);
            assignmentPerUserDto.setLastName(this.lastName);
            assignmentPerUserDto.setPesel(this.pesel);
            return assignmentPerUserDto;
        }
    }

    public static AssignmentPerAssetDtoBuilder builder() {
        return new AssignmentPerAssetDtoBuilder();
    }
}
