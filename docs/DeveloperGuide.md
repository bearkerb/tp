---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `dtenant 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("dtenant 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `dtenant 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteTenantCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteTenantCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteTenantCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddTenantCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddTenantCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddTenantCommandParser`, `DeleteTenantCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.estatemate.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `dtenant 5` command to delete the 5th person in the address book. The `dtenant` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `dtenant 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `tenant n/David …​` to add a new person. The `tenant` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `tenant n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `dtenant`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Property managers and property management companies managing multiple rental units.
* Needs to track and update tenant details efficiently (contact info, lease, rent, maintenance jobs).
* Needs a reliable system to manage both tenants and property maintenance.
* Prefers a fast, keyboard-driven desktop application.
* Comfortable with structured data entry and managing records through commands.

**Value proposition**:
EstateMate helps property managers stay organized and in control of their operations.
It provides a ***centralized and efficient way*** to manage tenants, rental records, and maintenance jobs all froma simple, keyboard-friendly interface.
With EstateMate, users can:
- Maintain accurate and organised tenant records
- Monitor rental payments and lease durations
- Track maintenance jobs and their completion status
- Improve operational efficiency through a command-driven interface

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ... | I want to ...                                                              | So that I can...                                                           |
|---|---|----------------------------------------------------------------------------|----------------------------------------------------------------------------|
| '***' | Property Manager | Add tenants and their info                                                 | manage tenants for contacting and tracking their info                      |
| '***' | Property Manager | Delete tenants and their info                                              | remove people who are no longer tenants                                    |
| '***' | Property Manager | List all tenants                                                           | see what tenants I am keeping track of                                     |
| '***' | Property Manager | View all available commands easily                                         | refer to proper syntax and command usage should I forget any               |
| '***' | Property Manager | Manage maintenance jobs and their status (completed/not completed)         | keep track of the status of maintenance jobs                               |
| '***' | Property Manager | Track rent information, due dates                                          | keep track of whose rent is due and make collections on time               |
|---|---| ---                                                                        | ---                                                                        |
| '**' | Property Manager | Search a contact of tenant by name (without the need to type in full name) | I can easily search for the tenant in a long list                          |
| '**' | Property Manager | Edit the details of the tenants                                            | make changes to tenants' details easily ie. when they change phone numbers |
| '**' | Property Manager | Assign maintenance jobs to tenants that need to have them resolved         | so that I know what outstanding jobs there are and to whom they concern    |
| '**' | Property Manager | Record payments by each tenant                                             | so that I don't miss payments                                              |

### Use cases
<br>

# Use Cases

(For all **use cases** below, the **System** is the 'TenantManager' and the **Actor** is the 'user', unless specified otherwise)

---

## Use case: Add a tenant

**MSS**
1. User requests to add a tenant with all required information.
2. **System** checks for correct parameter format and completeness.
3. **System** verifies that a tenant with the same name does not already exist.
4. **System** creates a new tenant record.
5. **System** shows the `"New tenant added"` success message and refreshes the tenants list automatically.

Use case ends.

**Extensions**
* 2a. The command format is wrong (e.g., missing parameter, wrong date format).
    * 2a1. **System** shows an error message and does not add the tenant (e.g. `"Invalid Command Format"`)
    Use case ends.
* 3a. A tenant with the same name already exists.
    * 3a1. **System** shows the error message: `"This tenant already exists in the address book"`.
    Use case ends.

---

## Use case: Delete a tenant

**MSS**
1. User requests to delete a tenant by specifying a **TENANT_NUMBER** using the command `dtenant TENANT_NUMBER`.
2. **System** verifies that the provided **TENANT_NUMBER** is a positive integer and corresponds to an existing tenant.
3. **System** removes the tenant record associated with the **TENANT_NUMBER** and all linked jobs.
4. **System** shows the success message: `"Deleted tenant successfully: <deleted tenant information>"`
5. **System** refreshes the list of tenants automatically.

Use case ends.

**Extensions**
* 2a. The provided **TENANT_NUMBER** is empty or is not a positive number.
    * 2a1. **System** shows the error message: `"Invalid tenant index detected."`.
    Use case ends.
* 2b. The provided **TENANT_NUMBER** does not correspond to any existing tenant.
    * 2b1. **System** shows the error message: `"Invalid command format!"`<br> 
        `"<description of usage>"` <br> `"<required parameter>"` <br> `"<example>"`.
    Use case ends.

---

## Use case: Edit a tenant

**MSS**
1. User request to edit a tenant by entering `edit TENANT_NUMBER` with one or more updated fields.
2. **System** verifies that the provided **TENANT_NUMBER** is valid and corresponds to an existing tenant.
3. **System** updates only the specified fields of the tenant's record while keeping other details unchanged.
4. **System** shows the success message: `"Edited Person: <tenant information with updated field>"`.
5. **System** automatically updates the tenant list display to reflect the changes.

Use case ends.

**Extensions**
* 2a. The **TENANT_NUMBER** is empty or is not a positive integer
    * 2a1. **System** shows the error message: `"Invalid command format!"` <br> 
      `"<description of usage>"` <br> `"<required parameters>"` <br> `"<example>"`.
    Use case ends.
* 2b. The provided **TENANT_NUMBER** does not correspond to any existing tenant in the list.
    * 2b1. **System** shows the error message: `"Invalid tenant index detected"`.
    Use case ends.
* 2c. The command does not include any fields to edit 
    * 2c1. **System** shows the error message: `"At least one field to edit must be provided"`.
    Use case ends.

---

## Use case: Add maintenance job

**MSS**
1. User requests to add a maintenance job, providing the description of job using the command `job d/DESCRIPTION`.
2. **System** verifies that **DESCRIPTION** is provided and not empty.
3. **System** checks if a maintenance job with the exact same **DESCRIPTION** already exists to prevent duplication.
4. **System** creates a new maintenance job and sets its status to `"Pending"` in the job list.
5. **System** shows the success message: `"New job added: #[JOB_NUMBER] [DESCRIPTION]"`.
6. System automatically updates the job list display to include the newly added job.

Use case ends.

**Extensions**
* 2a. The **DESCRIPTION** is missing or empty.
    * 2a1. **System** shows the error message: `"Description should not be blank."`.
    Use case ends.
* 3a. A duplicate maintenance job (same **DESCRIPTION**) is found.
    * 3a1. **System** shows the error message: `"This job already exists."`.
    Use case ends.

---

## Use case: Delete maintenance job for a unit

**MSS**
1. User requests to delete a maintenance job using the command `djob JOB_NUMBER`.
2. **System** verifies that the **JOB_NUMBER** is a positive integer and corresponds to an existing job in the job list.
3. **System** deletes the maintenance job associated with the specified **JOB_NUMBER**
4. **System** shows the success message: `"Deleted job: #[JOB_NUMBER]"`
5. **System** automatically updates the job list to reflect the removal.

Use case ends.

**Extensions**
* 2a. The **JOB_NUMBER** is empty or is not a positive integer.
    * 2a1. **System** shows the error message: `"Invalid command format!"` <br>
      `"<description of usage>"` <br> `"<required parameter>"` <br> `"<example>"`.
    Use case ends.
* 2b. The **JOB_NUMBER** does not correspond to any existing job.
    * 2b1. **System** shows the error message: `"Job index provided is invalid"`.
    Use case ends.

---

## Use case: Mark maintenance job

**MSS**
1. User requests to mark a maintenance job as completed by using the command `mark JOB_NUMBER`.
2. **System** verifies that the **JOB_NUMBER** is a positive integer and corresponds to an existing, unmarked job.
3. **System** sets the status of the maintenance job to "Completed".
4. **System** shows the success message: `"Marked job as complete: [JOB_NUMBER]"`
5. **System** automatically updates the maintenance job list display to reflect the new status.

Use case ends.

**Extensions**
* 2a. The **job number** is empty or is not a positive integer.
    * 2a1. **System** shows the error message: `"Invalid command format!"` <br>
      `"<description of usage>"` <br> `"<required parameter>"` <br> `"<example>"`.
      Use case ends.
* 2b. The **job number** does not correspond to any existing job in the job list
    * 2b1. **System** shows an error message: `"The job index provided is invalid"`.
      Use case ends.
* 2c. The job is already marked as completed.
    * 2c1. **System** shows the message: `"Job is already marked as done!"`.

---

## Use case: Unmark maintenance job

**MSS**
1. User requests to revert a maintenance job as not completed by using the command `unmark JOB_NUMBER`.
2. **System** verifies that the **JOB_NUMBER** is a positive integer and corresponds to an existing, unmarked job.
3. **System** sets the status of the maintenance job to "Pending" in the job list and "Not completed" in any linked tenant's job list.
4. **System** shows the success message: `"Marked job as incomplete: [JOB_NUMBER]"`
5. **System** automatically updates the maintenance job list display to reflect the new status.

Use case ends.

**Extensions**
* 2a. The **job number** is empty or is not a positive integer.
    * 2a1. **System** shows the error message: `"Invalid command format!"` <br>
      `"<description of usage>"` <br> `"<required parameter>"` <br> `"<example>"`.
      Use case ends.
* 2b. The **job number** does not correspond to any existing job in the job list.
    * 2b1. **System** shows an error message: `"The job index provided is invalid"`.
      Use case ends.
* 2c. The job is already marked as not completed.
    * 2c1. **System** shows the message: `"Job is already unmarked!"`.

---

## Use case: List all tenants

**MSS**
1. User requests to view all tenant information by using the `list` command.
2. **System** retrieves all existing tenant records from the database.
3. **System** displays the full list of tenants, including all details for each tenant.

Use case ends.

**Extensions**
* 1a. The command contains errors (e.g., wrong spelling).
    * 1a1. **System** shows the error message: `"Unknown command"`.
    Use case ends.
* 2a. No tenants exist in the **System**.
    * 2a1. **System** displays a message indicating that the list is empty.
    Use case ends.

---

## Use case: List all maintenance jobs

**MSS**
1. User requests to view all maintenance jobs by using the `ljob` command.
2. **System** retrieves all existing job records from the database.
3. **System** displays the full list of maintenance jobs, including the completion status of each job.

Use case ends.

**Extensions**
* 1a. The command contains errors (e.g., wrong spelling).
    * 1a1. **System** shows the error message: `"Unknown command"`.
    Use case ends.
* 2a. No jobs exist in the **System**.
    * 2a1. **System** displays a message: `"Listed all jobs: 0"`
    Use case ends.

---

## Use case: Link maintenance job to tenant

**MSS**
1. User requests to link an existing maintenance job to a tenant by using the command `link TENANT_NUMBER j/JOB_NUMBER`.
2. **System** verifies that the **TENANT_NUMBER** corresponds to an existing tenant and that the JOB_NUMBER corresponds to an existing maintenance job.
3. **System** links the specified job to the tenant updating the tenant's assigned job list and maintaining the job status.
4. **System** shows a success message: `"New Job linked: <specified tenant information>; Jobs: [JOB_NUMBER]"`
5. **System** updates the UI to reflect the linked job under the tenant's assigned jobs.

Use case ends.

**Extensions**
* 2a. **TENANT_NUMBER** or **JOB_NUMBER** is empty or not a positive integer 
    * 2a1. **System** shows the error message: `"Invalid command format!"` <br>
      `"<description of usage>"` <br> `"<required parameters>"` <br> `"<example>"`.
    Use case ends.
* 2b. **TENANT_NUMBER** does not correspond to any existing tenant.
    * 2b1. **System** shows the error message: `"Invalid tenant index detected"`.
    Use case ends.
* 2c. **JOB_NUMBER** does not correspond to any existing job.
    * 2c1. **System** shows the error message: `"The job index provided is invalid"`.
    Use case ends.
* 2d. The job is already linked to tenant.
    * 2d1. **System** shows the error message: `"This job is already linked to this tenant!"`.
    Use case ends.

---

## Use case: Help command

**MSS**
1. User requests a list of all available commands using the `help` command.
2. **System** retrieves the list and formats it.
3. **System** displays the list of "All available commands" including their formats.

Use case ends.

**Extensions**
* 1a. The command contains errors (e.g., wrong spelling).
    * 1a1. **System** shows the error message: `"Unknown command"`.
      Use case ends.

*{More to be added}*

## Non-Functional Requirements

### Performance
1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
> **Note:** within 1 second for typical operations like listing, searching, adding, etc.
3.  Users with above-average typing speed should be able to perform most tasks **faster via commands** than using a mouse.

### Usability
1. The system should provide **clear, informative error messagaes to guide user** for invalid inputs.
2. Commands and outputs should follow a consistent format to minimize confusion.

### Reliability
1. Data should not be lost in case of sudden termination.
2. The system should handle invalid inputs without crashing or corrupting.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `dtenant 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `dtenant 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect dtenant commands to try: `dtenant`, `dtenant x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
