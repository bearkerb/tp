---
layout: default.md
title: User Guide
pageNav: 3
---

# Welcome to EstateMate User Guide!

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

[1. Introduction](#1-introduction)
- [1.1 What Is EstateMate](#1-1-what-is-estatemate)
- [1.2 User Proficiency and Expectations](#1-2-user-proficiency-and-expectations)
- [1.3 Why This User Guide Matters](#1-3-why-this-user-guide-matters)

[2. Quick Start](#2-quick-start)
- [2.1 Installation](#2-1-installation)
- [2.2 User Interface Overview](#2-2-user-interface-overview)
- [2.3 Understanding Command Prefix](#2-3-understanding-command-prefix)

[3. Features](#3-features)
- [3.1 Tenant Management](#3-1-tenant-management)
    - [3.1.1 Adding a Tenant](#3-1-1-adding-a-tenant-tenant)
    - [3.1.2 Deleting a Tenant](#3-1-2-deleting-a-tenant-dtenant)
    - [3.1.3 Editing a Tenant](#3-1-3-editing-a-tenant-edit)
    - [3.1.4 Finding a Tenant](#3-1-4-finding-a-tenant-find)
    - [3.1.5 Listing All Tenants](#3-1-5-listing-all-tenants-list)
- [3.2 Maintenance Job Management](#3-2-maintenance-job-management)
    - [3.2.1 Adding a Job](#3-2-1-adding-a-job-job)
    - [3.2.2 Deleting a Job](#3-2-2-deleting-a-job-djob)
    - [3.2.3 Editing a Job](#3-2-3-editing-a-job-ejob)
    - [3.2.4 Finding a Job](#3-2-4-finding-a-job-fjob)
    - [3.2.5 Linking Job To Tenant](#3-2-5-linking-job-to-tenant-link)
    - [3.2.6 Listing All Jobs](#3-2-6-listing-all-jobs-ljob)
    - [3.2.7 Marking Job As Completed](#3-2-7-marking-job-as-completed-mark)
    - [3.2.8 Marking Job As Not Completed](#3-2-8-marking-job-as-not-completed-unmark)
- [3.3 General Utilities](#3-3-general-features)
    - [3.3.1 Clearing All Tenants](#3-3-1-clearing-all-tenants-clear)
    - [3.3.2 Exiting Application](#3-3-2-exiting-application-exit)
    - [3.3.3 Getting Help](#3-3-3-getting-help-help)

[4. Command Summary](#4-command-summary)

[5. FAQ and Known Issues](#5-faq-and-known-issues)
- [5.1 FAQ](#5-1-faq)
- [5.2 Known Issues](#5-2-known-issues)

[6. Glossary](#6-glossary)

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction
<br>

### 1.1 What Is EstateMate
<p>
EstateMate is a comprehensive application and management tool designed specifically 
for property managers and its executive firms. 
</p>

<br>

### 1.2 User Proficiency and Expectations
- ***Professional Focus:*** EstateMate is designed for property managers who need an efficient way to track tenant contacts
  and manage maintenance tasks.
- ***Efficiency:*** Built specifically for the property management workflow, EstateMate prioritizes fast,
  streamlined operations so managers can update tenant information and track maintenance jobs.
- ***User-Friendly:*** Whether you are familiar with command-line tools or prefer a simple graphical interface,
  EstateMate is designed to be intuitive and straightforward, ensuring smooth property management.

<br>

### 1.3 Why This User Guide Matters
<p>
This guide is designed to help property managers make the most out of EstateMate's features.
Managing tenant contacts and tracking maintenance tasks efficiently is essential for smooth property operations.
Inside, you will find step-by-step instructions, examples, and helpful tips to streamline your workflow so that you can 
keep your properties running smoothly.
</p>

--------------------------------------------------------------------------------------------------------------------

## 2. Quick start
<br>

### 2.1 Installation

1. Ensure you have Java `17` or above installed in your computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2526S1-CS2103T-F08a-2/tp/releases/tag/v1.3).

3. Copy the file to the folder you want to use as the _home folder_ for your application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar estatemate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <img src="images/Updated_GUI_v1.png" alt="Updated GUI v1" width="600">

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `tenant n/John Tan p/91234567 e/jtan@example.com a/Blk 123 #12-34, Bedok lease/2025-01-01 2026-12-31 r/2800.00 paydate/2025-01-01`:
      Adds a contact named `John Tan` to the application.

    * `dtenant 3` : Deletes the 3rd tenant contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the application.<br>

6. Refer to the [Features](#3-features) below for details of each command.

<br>

### 2.2 User Interface Overview

- insert UI (coming soon...)

<br>

### 2.3 Understanding Command Prefix

In EstateMate, commands use **prefixes** to identify each parameter.  
Each prefix must be followed by a `/` and its corresponding value.

| **Prefix** | **Meaning**     | **Example Usage**             |
|------------|-----------------|-------------------------------|
| `n/`       | Name            | `n/John Tan`                  |
| `p/`       | Phone Number    | `p/91234567`                  |
| `e/`       | Email Address   | `e/jtan@example.com`          |
| `a/`       | Address         | `a/Blk 123 #12-34`            |
| `lease/`   | Lease Start-End | `lease/2025-01-01 2026-12-31` |
| `r/`       | Amount          | `r/2800.00`                   |
| `paydate/` | PayDate         | `paydate/2025-01-01`          |
| `t/`       | Tag             | `t/friend`                    |
| `j/`       | Job Number | `j/2`                          |
| `d/`      | Description  | `d/Broken pipe`        |

💡**Tip:**<br>
Combine multiple prefixes in one command:<br>
`tenant n/John Tan p/91234567 e/jtan@example.com a/Blk 123 #12-34, Bedok lease/2025-01-01 2026-12-31 r/2800.00 paydate/2025-01-01`

--------------------------------------------------------------------------------------------------------------------

## 3. Features

This section provides a comprehensive overview of the features available in **EstateMate**.  
It is designed to help property managers understand how to efficiently manage tenant information and maintenance jobs
through various commands.

Each feature is grouped by functionality:
- **Command Summary** — Quick reference table of all available commands.
- **Tenant Management** — Commands for adding, deleting, editing, and finding tenant details.
- **Maintenance Job Management** — Commands for creating, tracking, and updating maintenance jobs, including linking them to tenants.
- **General Utilities** — Commands for clearing data, exiting the application, and accessing in-app help.

<div style="border-left: 4px solid #FFB300; background-color: #FFF8E1; padding: 15px;">
⚠️ <strong>Important Notes about Command Format:</strong><br><br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<br><br>

### 3.1 Tenant Management 
<br>

#### 3.1.1 Adding a Tenant: `tenant`
Adds a tenant to the application.

Format: `tenant n/NAME p/PHONE e/EMAIL a/ADDRESS lease/START END r/AMOUNT paydate/PAYDATE [t/TAG]…​`

📌**Note:** A tenant can have any number of tags, including zero.

Examples:
- `tenant n/John Tan p/91234567 e/jtan@example.com a/Blk 123 #12-34, Bedok lease/2025-01-01 2026-12-31 r/2800.00 paydate/2025-01-01`
- `tenant n/Sarah Kim p/12398653 e/sarahk@example.com a/Blk 234 #56-78, Clementi lease/2025-02-02 2027-02-02 r/4000.00 paydate/2025-02-02`

💡**Tip:** Tag tenants so that you can use `find` to quickly filter and locate them without checking the full list.
<br>

#### 3.1.2 Deleting a Tenant: `dtenant`
Deletes the specified tenant from the application.

Format: `dtenant TENANT_NUMBER`

📌**Note:** 
- `TENANT_NUMBER` is the index displayed next to each tenant in the tenant list, and must be ***positive number***.
- Only tenants that exist in the current displayed list can be deleted.

<div style="border-left: 4px solid red; background-color: #ffe6e6; padding: 15px;">

<strong>❗ Warning:</strong><br>
- This action is irreversible, data will be ***permanently deleted***.<br>
- Once a tenant is deleted, any links to jobs associated with that tenant will also be removed.
</div>
<br>

Examples:
- `list` followed by `dtenant 2` deletes the 2nd tenant listed in the application.
- `find John` followed by `dtenant 1` deletes the 1st tenant in the results of the `find` command.

💡**Tip:**
- Use `list` or `find` first to confirm the correct tenant before deleting to avoid accidental removal.
- If you are deleting multiple tenants, consider double-checking indices after each deletion, as the list order updates dynamically.
<br>

#### 3.1.3 Editing a Tenant: `edit`

Edits an existing tenant in the application.

Format: `edit TENANT_NUMBER [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lease/LEASE] [r/AMOUNT] [paydate/PAYDATE] [t/TAG]...[j/JOB]...[t/TAG]…​`

📌**Note:**
- `TENANT_NUMBER` is the index displayed next to each tenant in the tenant list, and must be ***positive number***.
- Provide ***at least one*** field to edit.
- Tags are replaced, not added cumulatively; t/ clears all tags.
- You can remove all the person’s tags by typing `t/` without
   specifying any tags after it.

Examples:
- `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st tenant to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` edits the name of the 2nd tenant to be `Betsy Crower` and clears all existing tags.<br>

💡**Tip:**
- Use `list` or `find` first to confirm the correct tenant before editing to avoid overwriting important data. 
- When updating multiple fields, include all changes in a single command to reduce errors.
<br>

#### 3.1.4 Finding a Tenant: `find`

Find tenants whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

📌**Note:**
- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Tenants matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
- `find John` returns `john` and `John Doe`.
- `find alex david` returns `Alex Yeoh`, `David Li`.<br>

💡**Tip:**
- Use specific keywords to narrow results, especially for common names.
- Combine multiple keywords when you want to search for multiple tenants at once.
<br>

#### 3.1.5 Listing All Tenants: `list`
Displays a list of all tenants currently stored in the application, ordered from the earliest added to the most recent.

Format: `list`

📌**Note:**
- Any additional input after `list` command will be ignored.
- `list` without any tenants will show you empty list, hence recommend you to add some tenants to the application.
<br><br>

### 3.2 Maintenance Job Management 
<br>

#### 3.2.1 Adding a Job: `job`
Adds a maintenance job to the job list in the application.

Format: `job d/DESCRIPTION`

📌**Note:**
- Provide a ***clear and concise*** description of the maintenance issue.
- Jobs can later be linked to tenants for easy tracking.

Examples:
- `job d/Water leakage in ceiling`
- `job d/Pipe leakage`<br>

💡**Tip:**
- Use consistent wording for similar issues (e.g., "Pipe leakage" vs "Leaking pipe") to make searching easier later.
- Add jobs as soon as issues are reported to keep tenant records up-to-date.
<br>

#### 3.2.2 Deleting a Job: `djob`
Deletes a maintenance job from the application.

Format: `djob JOB_NUMBER`

📌**Note:**
- `JOB_NUMBER` is the index displayed next to each job in the job list, and must be ***positive number***.
- Only jobs that exist in the current displayed list can be deleted.
- Deleting a job removes it from any tenants’ assigned job lists.

<div style="border-left: 4px solid red; background-color: #ffe6e6; padding: 15px;">

<strong>❗ Warning:</strong><br>
- This action is irreversible, job will be ***permanently deleted***. 
- Once a job is deleted, any links to tenants associated with that job will also be removed, and it will no longer appear in the tenant’s job list.
</div>
<br>

Examples:
* `djob 2` deletes the job with job number 2 if it exists.

💡**Tip:**
- Use `ljob` to double-check the job number before deleting to avoid removing the wrong job.
- If you may need a record of the job later, consider marking it as completed (mark) instead of deleting.

#### 3.2.3 Editing a Job: `ejob`
Edits a maintenance job from the application.

Format: `ejob JOB_NUMBER d/DESCRIPTION`

📌**Note:**
- `JOB_NUMBER` is the index displayed next to each job in the job list, and must be ***positive number***.
- Only jobs that exist in the current displayed list can be edited.
- Provide a ***clear and concise*** description of the maintenance issue.

Examples:
* `ejob 3 d/fix faucet` changes the description of the job with job number 3 to "fix faucet".

💡**Tip:**
- Use descriptive keywords in the description to make it easier to find the job later with fjob.
- You can quickly edit a job after using `ljob` to confirm the job number.

#### 3.2.4 Finding a Job: `fjob`
Find maintenance jobs whose descriptions contain any of the given keyword.

Format: `fjob KEYWORD [MORE KEYWORDS]`

📌**Note:**
- The search is case-insensitive. e.g `pipe` will match `Pipe`
- The order of the keywords does not matter. e.g. `Pipe leak` will match `leak pipe`
- Only the description is searched.
- Only full words will be matched e.g. `Pipe` will not match `Pipes`
- Maintenance jobs matching at least one keyword will be returned (i.e. `OR` search).
   e.g. `Broken window` will return `Broken pipe`, `Window repair`

Examples:
- `fjob pipe` returns `pipe` and `Pipe Leakage`.
- `fjob aircon repair` returns `Aircon Leakage` and `Repair Pipe`.

💡**Tip:**
- Use specific keywords related to the job description to narrow down results, e.g., pipe leakage instead of just pipe.
- Combine multiple keywords for broader search if you’re unsure of the exact description, e.g., aircon repair ceiling.

#### 3.2.5 Linking Job to Tenant: `link`
Link a maintenance job to a specific tenant.

Format: `link TENANT_NUMBER j/JOB_NUMBER`

📌**Note:**
- `JOB_NUMBER` is the index displayed next to each job in the job list.
- You can only link jobs and tenants that already exist in the system.
- Once linked, the job will appear under the tenant’s assigned jobs in the display.
- Deleting a linked job will also remove it from the tenant’s assigned job list.
- Marking and unmarking linked job will change the status of completion under tenant's assigned job list.

Examples:
- `link 2 j/5` links the 5th maintenance job in the job list to the 2nd tenant in the tenant list.

💡**Tip:**
- Link jobs as soon as they are created to keep tenants’ maintenance records accurate and avoid losing track of pending tasks.

#### 3.2.6 Listing All Jobs: `ljob`
Displays a list of all jobs currently stored in the application, ordered from the earliest added to the most recent.

Format: `ljob`

📌**Note:**
- Any additional input after `ljob` command will be ignored.
- `list` without any jobs will show you empty list, hence recommend you to add some maintenance jobs to the application.

#### 3.2.7 Marking Job as Completed: `mark`
Updates the status of a maintenance job so that completed tasks can be tracked easily.

Format: `mark JOB_NUMBER`

📌**Note:**
- `JOB_NUMBER` is the index displayed next to each job in the job list, and must be ***positive number***.
- Once marked, the job status will be updated in the display under any linked tenant.
- If a job is marked by mistake, you can use the `unmark` command to revert it as not completed.

Examples:
- `mark 3` updates the completion status of job number 3 of the job list to completed.

#### 3.2.8 Marking Job as Not Completed: `unmark`
Revert a maintenance job's status to not completed in case it was marked as complete by mistake.

Format: `unmark JOB_NUMBER`

📌**Note:**
- `JOB_NUMBER` is the index displayed next to each job in the job list, and must be ***positive number***.
- Once unmarked, the job will no longer appear as completed under any linked tenant.

Examples:
- `unmark 3` updates the status of job number 3 of the job list back to not completed.

💡**Tip:** 
- Use `ljob` to double-check job list before using `unmark` to ensure you’re updating the correct job.
- Use unmark to quickly revert mistakes when a job was accidentally marked as completed.

<br>

### 3.3 General Utilities 
<br>

#### 3.3.1 Clearing All Tenants: `clear`
Remove all tenants from the application.

Format: `clear`

<div style="border-left: 4px solid red; background-color: #ffe6e6; padding: 15px;">

<strong>❗ Warning:</strong><br>
This action is irreversible, all data will be ***permanently deleted***. Make sure you have backed up any important tenant or job information before using this command.
</div>
<br>

💡**Tip:** Use clear only when you want to start fresh or reset your tenant list.
<br>

#### 3.3.2 Exiting Application: `exit`
Closes the program/application.

Format: `exit`

📌**Note:** All data entered will be automatically saved to storage.
<br>

#### 3.3.3 Getting Help: `help`
Opens the help window which provides link accessing the user guide and get assistance with commands. This command is useful if you need a quick reminder on how to use the available commands.

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## 4. Command Summary
<br>

Action                      | Format                                                                             |  Examples
----------------------------|------------------------------------------------------------------------------------|--------------------------------------------------
**Add Tenant**              | `tenant n/NAME p/PHONE e/EMAIL a/ADDRESS lease/START END r/AMOUNT paydate/PAYDATE` | `tenant n/John Tan p/91234567 e/jtan@example.com a/Blk 123 #12-34, Bedok lease/2025-01-01 2026-12-31 r/2800.00 paydate/2025-01-01`
**Add Job**                 | `job d/DESCRIPTION`                                                                | `job d/Water leakage in ceiling`                                                           
**Clear**                   | `clear`                                                                            
**Delete Tenant**           | `dtenant TENANT_NUMBER`                                                            | `dtenant 3`                                                                             
**Delete Job**              | `djob JOB_NUMBER`                                                                  | `djob 3`                                                                                     
**Edit Tenant**             | `edit TENANT_NUMBER [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`               | `edit 2 n/James Lee e/jameslee@example.com` 
**Edit Job**                | `ejob JOB_NUMBER d/DESCRIPTION`                                                    | `ejob 3 d/fix faucet`                     
**Exit**                    | `exit`                                                                             |
**Find Tenant**             | `find KEYWORD [MORE_KEYWORDS]`                                                     | `find James Jake`                         
**Find Job**                | `fjob KEYWORD [MORE_KEYWORDS]`                                                     | `fjob electrical plumbing renovation`     
**Help**                    | `help`                                                                             |
**Link Job to Tenant**      | `link TENANT_NUMBER j/JOB_NUMBER`                                                  | `link 1 j/2`                            
**List Tenants**            | `list`                                                                             | 
**List Jobs**               | `ljob`                                                                             | 
**Mark Job**                | `mark JOB_NUMBER`                                                                  |`mark 1`                                                
**Unmark Job**              | `unmark JOB_NUMBER`                                                                | `unmark 1`

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ and Known Issues
<br>

### 5.1 FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous EstateMate home folder.

<br>

### 5.2 Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## 6. Glossary
<br>

Terms                |  Definitions 
---------------------|------------------------------------------------------------------------------------------
Upper-Case Parameter | A placeholder in commands (e.g., NAME , PHONE ) that must be replaced with actual values.

<br>
