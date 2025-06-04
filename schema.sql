-- Database Schema for Contact Directory

-- Table to store categories (Floors, SubOffices, OtherOffices)
CREATE TABLE categories (
    category_id VARCHAR(255) PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    category_type VARCHAR(50) NOT NULL, -- 'Floor', 'SubOffice', or 'OtherOffice'
    color VARCHAR(50)
);

-- Table to store sections within each category
CREATE TABLE sections (
    section_id VARCHAR(255) PRIMARY KEY,
    section_name VARCHAR(255) NOT NULL,
    category_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);

-- Table to store contact details
CREATE TABLE contacts (
    contact_pk INTEGER PRIMARY KEY AUTOINCREMENT,
    designation VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    call_sign VARCHAR(100),
    landline VARCHAR(50),
    contact1 VARCHAR(50) NOT NULL, -- Official contact
    contact2 VARCHAR(50),         -- Personal contact
    email VARCHAR(255),
    details TEXT,
    section_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (section_id) REFERENCES sections(section_id) ON DELETE CASCADE
);

-- Optional: Indexes for frequently queried columns
CREATE INDEX idx_contacts_name ON contacts(name);
CREATE INDEX idx_contacts_designation ON contacts(designation);
CREATE INDEX idx_sections_category_id ON sections(category_id);
CREATE INDEX idx_contacts_section_id ON contacts(section_id);

-- Sample data insertion comments (actual data would be populated from JSON)
-- Example for a category:
-- INSERT INTO categories (category_id, category_name, category_type, color) VALUES ('ground', 'Ground Floor', 'Floor', 'bg-blue-800');

-- Example for a section:
-- INSERT INTO sections (section_id, section_name, category_id) VALUES ('ground-admin', 'Administration Section', 'ground');

-- Example for a contact:
-- INSERT INTO contacts (designation, name, call_sign, landline, contact1, contact2, email, details, section_id)
-- VALUES ('Chief of Administration', 'Aakash Sharma', 'Alpha-1', '01-4223344', '9841123456', '9851123456', 'aakash.sharma@kathmandupolice.gov.np', 'Office hours: 9AM-5PM, Sunday-Friday', 'ground-admin');

/*
Analysis Summary:

1.  **Categories Table (`categories`):**
    *   Stores top-level groupings: "Floors", "SubOffices", and "OtherOffices".
    *   `category_id`: Primary key, derived from the `id` field in the JSON (e.g., "ground", "circle1").
    *   `category_name`: Name of the category (e.g., "Ground Floor").
    *   `category_type`: A new field to differentiate the source/type of category (e.g., "Floor", "SubOffice", "OtherOffice"). This is important because IDs might not be globally unique across the different JSON files otherwise, though in this specific dataset they appear to be.
    *   `color`: Stores the color information associated with the category.

2.  **Sections Table (`sections`):**
    *   Stores the "sections" within each category.
    *   `section_id`: Primary key, derived from the `id` field of a section object (e.g., "ground-admin").
    *   `section_name`: Name of the section (e.g., "Administration Section").
    *   `category_id`: Foreign key linking to the `categories` table, establishing the parent-child relationship.

3.  **Contacts Table (`contacts`):**
    *   Stores individual contact details.
    *   `contact_pk`: An auto-incrementing integer primary key, as contacts in the JSON do not have their own unique IDs. This ensures each contact record is unique in the database.
    *   `designation`, `name`, `call_sign`, `landline`, `contact1` (Official), `contact2` (Personal), `email`, `details`: These fields directly map to the attributes of a contact object in the JSON.
    *   `section_id`: Foreign key linking to the `sections` table, indicating which section the contact belongs to.

Relationships:
*   One Category can have many Sections. (`categories` 1 -> N `sections`)
*   One Section can have many Contacts. (`sections` 1 -> N `contacts`)

This schema should effectively store and organize the provided JSON data in a relational database.
The use of `ON DELETE CASCADE` for foreign keys means that if a category is deleted, all its sections and their associated contacts will also be deleted. Similarly, if a section is deleted, all its contacts will be deleted. This helps maintain data integrity.
*/
