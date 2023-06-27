# Notes
### Dependencies
- Start of by including spring-boot-devtools, spring-boot-starter-web these 2 atleast. You can initialize using https://start.spring.io/ and importing or by using IDE Extensions (recommended)
- Extra Dependencies can be added using "Add Starters" using IDE Extensions or by manually adding them in pom.xml (OR) build.gradle

### Files
- pom.xml (OR) build.gradle decides the dependecies used
- src/main/resourdes/appplication.properties is used to set properties that can be accessed across project with ${variable} or to set the properties used by packages. Basically it is equivalent to config file
- java/com/packagename(example in this case)/projectname(springlearn in this case) wll hold the main file to run i.e. SpringlearnApplication.java

### Folder Structure
- java/com/packagename/projectname/feature (ToDO) is used to hold all details of the feature including controller, service etc.
- The other kind of approach is seperate out to controllers,services etc instead of feature

### Validation
- if groups is specified in Validators such as @NotBlank and input function does not have a @Validated class then no validation takes place
- @Validated should be given to class(without any group classes) and function (with group classes) for validation according to groups sepcified in the beans

### Other
- if something is imported but is told as null (for example the repostiory ) then use autowired to patch it up