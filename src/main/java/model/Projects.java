package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Projects {
    /**
     * Constructor without arguments.
     */
    public Projects(Integer projectId, String description, LocalDate dateAdded) {
        this.projectId = projectId;
        this.description = description;
        this.dateAdded = dateAdded;
    }
    public Projects() {
        this.dateAdded= LocalDate.now();
    }

    /**
     * Project id.
     */

    private Integer projectId;

    /**
     * Project description.
     */
    private String description;

    /**
     * Date adding of project.
     */
    private LocalDate dateAdded;

    public Integer getProjectId() {
        return projectId;
    }

    public Projects setProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Projects setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Projects setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", description='" + description + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
