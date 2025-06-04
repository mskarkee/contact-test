package com.example.contactdirectory.service;

import com.example.contactdirectory.dto.*;
import com.example.contactdirectory.model.Category;
import com.example.contactdirectory.model.Contact;
import com.example.contactdirectory.model.Section;
import com.example.contactdirectory.repository.CategoryRepository;
import com.example.contactdirectory.repository.ContactRepository;
import com.example.contactdirectory.repository.SectionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final CategoryRepository categoryRepository;
    private final SectionRepository sectionRepository;
    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

    // Hardcoded JSON strings
    private static final String FLOORS_JSON = """
    {
      "floors": [
        {
          "id": "ground",
          "name": "Ground Floor",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "ground-admin",
              "name": "Administration Section",
              "contacts": [
                {
                  "designation": "Chief of Administration",
                  "name": "Aakash Sharma",
                  "callSign": "Alpha-1",
                  "landline": "01-4223344",
                  "contact1": "9841123456",
                  "contact2": "9851123456",
                  "email": "aakash.sharma@kathmandupolice.gov.np",
                  "details": "Office hours: 9AM-5PM, Sunday-Friday"
                },
                {
                  "designation": "Deputy Chief",
                  "name": "Bimala Thapa",
                  "callSign": "Alpha-2",
                  "landline": "01-4223345",
                  "contact1": "9851234567",
                  "contact2": "9841234567",
                  "email": "bimala.thapa@kathmandupolice.gov.np",
                  "details": "In charge of personnel management"
                }
              ]
            },
            {
              "id": "ground-operations",
              "name": "Operations Section",
              "contacts": [
                {
                  "designation": "Operations Chief",
                  "name": "Dipak Joshi",
                  "callSign": "Bravo-1",
                  "landline": "01-4223355",
                  "contact1": "9841234567",
                  "contact2": "9851234567",
                  "email": "dipak.joshi@kathmandupolice.gov.np",
                  "details": "24/7 emergency contact available"
                }
              ]
            }
          ]
        },
        {
          "id": "floor1",
          "name": "Floor 1",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor1-crime",
              "name": "Crime Investigation",
              "contacts": [
                {
                  "designation": "Crime Investigation Chief",
                  "name": "Gopal Shrestha",
                  "callSign": "Charlie-1",
                  "landline": "01-4223366",
                  "contact1": "9842345678",
                  "contact2": "9852345678",
                  "email": "gopal.shrestha@kathmandupolice.gov.np",
                  "details": "Specialized in homicide cases"
                }
              ]
            }
          ]
        },
        {
          "id": "floor2",
          "name": "Floor 2",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor2-traffic",
              "name": "Traffic Police",
              "contacts": [
                {
                  "designation": "Traffic Police Chief",
                  "name": "Jeevan Adhikari",
                  "callSign": "Delta-1",
                  "landline": "01-4223377",
                  "contact1": "9843456789",
                  "contact2": "9853456789",
                  "email": "jeevan.adhikari@kathmandupolice.gov.np",
                  "details": "Manages all traffic operations"
                }
              ]
            }
          ]
        },
        {
          "id": "floor3",
          "name": "Floor 3",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor3-hr",
              "name": "Human Resources",
              "contacts": [
                {
                  "designation": "HR Director",
                  "name": "Tara Devi",
                  "callSign": "Echo-1",
                  "landline": "01-4223411",
                  "contact1": "9846789012",
                  "contact2": "9856789012",
                  "email": "tara.devi@kathmandupolice.gov.np",
                  "details": "Manages all HR operations"
                }
              ]
            }
          ]
        },
        {
          "id": "floor4",
          "name": "Floor 4",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor4-training",
              "name": "Training Section",
              "contacts": [
                {
                  "designation": "Training Director",
                  "name": "Yogesh Karki",
                  "callSign": "Foxtrot-1",
                  "landline": "01-4223422",
                  "contact1": "9847890123",
                  "contact2": "9857890123",
                  "email": "yogesh.karki@kathmandupolice.gov.np",
                  "details": "Oversees all training programs"
                }
              ]
            }
          ]
        },
        {
          "id": "floor5",
          "name": "Floor 5",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor5-welfare",
              "name": "Welfare Section",
              "contacts": [
                {
                  "designation": "Welfare Officer",
                  "name": "Bikram Rana",
                  "callSign": "Golf-1",
                  "landline": "01-4223433",
                  "contact1": "9848901234",
                  "contact2": "9858901234",
                  "email": "bikram.rana@kathmandupolice.gov.np",
                  "details": "Officer welfare programs"
                }
              ]
            }
          ]
        },
        {
          "id": "floor6",
          "name": "Floor 6",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor6-forensic",
              "name": "Forensic Department",
              "contacts": [
                {
                  "designation": "Forensic Department Head",
                  "name": "Prakash Yadav",
                  "callSign": "Hotel-1",
                  "landline": "01-4223399",
                  "contact1": "9845678901",
                  "contact2": "9855678901",
                  "email": "prakash.yadav@kathmandupolice.gov.np",
                  "details": "Forensic science specialist"
                }
              ]
            }
          ]
        },
        {
          "id": "floor7",
          "name": "Floor 7",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor7-cyber",
              "name": "Cyber Crime",
              "contacts": [
                {
                  "designation": "Cyber Crime Chief",
                  "name": "Manoj Neupane",
                  "callSign": "India-1",
                  "landline": "01-4223388",
                  "contact1": "9844567890",
                  "contact2": "9854567890",
                  "email": "manoj.neupane@kathmandupolice.gov.np",
                  "details": "Leads cyber crime division"
                }
              ]
            }
          ]
        },
        {
          "id": "floor8",
          "name": "Floor 8",
          "color": "bg-blue-800",
          "sections": [
            {
              "id": "floor8-intelligence",
              "name": "Intelligence Section",
              "contacts": [
                {
                  "designation": "Intelligence Chief",
                  "name": "Suresh Lama",
                  "callSign": "Juliet-1",
                  "landline": "01-4223502",
                  "contact1": "9845678902",
                  "contact2": "9855678902",
                  "email": "suresh.lama@kathmandupolice.gov.np",
                  "details": "Manages intelligence operations"
                }
              ]
            }
          ]
        }
      ]
    }
    """;

    private static final String SUB_OFFICES_JSON = """
    {
      "subOffices": [
        {
          "id": "circle1",
          "name": "Kathmandu Central Circle",
          "color": "bg-green-700",
          "sections": [
            {
              "id": "circle1-admin",
              "name": "Administration Section",
              "contacts": [
                {
                  "designation": "Circle Chief",
                  "name": "Rajesh Hamal",
                  "callSign": "Kilo-1",
                  "landline": "01-4223444",
                  "contact1": "9849012345",
                  "contact2": "9859012345",
                  "email": "rajesh.hamal@kathmandupolice.gov.np",
                  "details": "Kathmandu Central Circle Administration"
                }
              ]
            },
            {
              "id": "circle1-operations",
              "name": "Operations Section",
              "contacts": [
                {
                  "designation": "Operations Head",
                  "name": "Anil Gurung",
                  "callSign": "Lima-1",
                  "landline": "01-4223445",
                  "contact1": "9840123456",
                  "contact2": "9850123456",
                  "email": "anil.gurung@kathmandupolice.gov.np",
                  "details": "Manages circle operations"
                }
              ]
            }
          ]
        },
        {
          "id": "circle2",
          "name": "Kathmandu East Circle",
          "color": "bg-green-700",
          "sections": [
            {
              "id": "circle2-patrol",
              "name": "Patrol Section",
              "contacts": [
                {
                  "designation": "Patrol Chief",
                  "name": "Gopal Pandey",
                  "callSign": "Mike-1",
                  "landline": "01-4223455",
                  "contact1": "9841234567",
                  "contact2": "9851234567",
                  "email": "gopal.pandey@kathmandupolice.gov.np",
                  "details": "Kathmandu East Circle Patrol"
                }
              ]
            }
          ]
        },
        {
          "id": "prabhag1",
          "name": "New Road Prabhag",
          "color": "bg-green-700",
          "sections": [
            {
              "id": "prabhag1-crime",
              "name": "Crime Prevention",
              "contacts": [
                {
                  "designation": "Prabhag In-Charge",
                  "name": "Krishna Shrestha",
                  "callSign": "November-1",
                  "landline": "01-4223466",
                  "contact1": "9842345678",
                  "contact2": "9852345678",
                  "email": "krishna.shrestha@kathmandupolice.gov.np",
                  "details": "New Road Prabhag Crime Prevention"
                }
              ]
            }
          ]
        },
        {
          "id": "ilaka1",
          "name": "Thamel Ilaka",
          "color": "bg-green-700",
          "sections": [
            {
              "id": "ilaka1-tourist",
              "name": "Tourist Police",
              "contacts": [
                {
                  "designation": "Ilaka In-Charge",
                  "name": "Sita Poudel",
                  "callSign": "Oscar-1",
                  "landline": "01-4223445",
                  "contact1": "9850123456",
                  "contact2": "9840123456",
                  "email": "sita.poudel@kathmandupolice.gov.np",
                  "details": "Thamel Ilaka Tourist Police"
                }
              ]
            }
          ]
        }
      ]
    }
    """;

    private static final String OTHER_OFFICES_JSON = """
    {
      "otherOffices": [
        {
          "id": "cyber1",
          "name": "Cyber Bureau",
          "color": "bg-purple-700",
          "sections": [
            {
              "id": "cyber1-investigation",
              "name": "Cyber Investigation",
              "contacts": [
                {
                  "designation": "Cyber Bureau Chief",
                  "name": "Niraj Basnet",
                  "callSign": "Papa-1",
                  "landline": "01-4223477",
                  "contact1": "9842345678",
                  "contact2": "9852345678",
                  "email": "niraj.basnet@kathmandupolice.gov.np",
                  "details": "Cyber crime investigations"
                }
              ]
            }
          ]
        },
        {
          "id": "fire1",
          "name": "Fire Office",
          "color": "bg-purple-700",
          "sections": [
            {
              "id": "fire1-response",
              "name": "Fire Response",
              "contacts": [
                {
                  "designation": "Fire Office Chief",
                  "name": "Ramesh K.C.",
                  "callSign": "Quebec-1",
                  "landline": "01-4223488",
                  "contact1": "9843456789",
                  "contact2": "9853456789",
                  "email": "ramesh.kc@kathmandupolice.gov.np",
                  "details": "Fire safety and response"
                }
              ]
            }
          ]
        },
        {
          "id": "water1",
          "name": "Water Supply Office",
          "color": "bg-purple-700",
          "sections": [
            {
              "id": "water1-management",
              "name": "Water Management",
              "contacts": [
                {
                  "designation": "Water Supply Coordinator",
                  "name": "Ujjwal Adhikari",
                  "callSign": "Romeo-1",
                  "landline": "01-4223499",
                  "contact1": "9844567890",
                  "contact2": "9854567890",
                  "email": "ujjwal.adhikari@kathmandupolice.gov.np",
                  "details": "Water supply management"
                }
              ]
            }
          ]
        }
      ]
    }
    """;


    @Autowired
    public DataInitializer(CategoryRepository categoryRepository,
                           SectionRepository sectionRepository,
                           ContactRepository contactRepository,
                           ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.sectionRepository = sectionRepository;
        this.contactRepository = contactRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional // Ensures all operations are part of a single transaction
    public void run(String... args) throws Exception {
        logger.info("Starting data initialization...");

        if (categoryRepository.count() > 0) {
            logger.info("Data already initialized. Skipping.");
            return;
        }

        loadFloors();
        loadSubOffices();
        loadOtherOffices();

        logger.info("Data initialization complete.");
    }

    private void loadFloors() throws Exception {
        logger.info("Loading floors data...");
        FloorsWrapperDto wrapper = objectMapper.readValue(FLOORS_JSON, FloorsWrapperDto.class);
        processCategories(wrapper.getFloors(), "Floor");
    }

    private void loadSubOffices() throws Exception {
        logger.info("Loading sub-offices data...");
        SubOfficesWrapperDto wrapper = objectMapper.readValue(SUB_OFFICES_JSON, SubOfficesWrapperDto.class);
        processCategories(wrapper.getSubOffices(), "SubOffice");
    }

    private void loadOtherOffices() throws Exception {
        logger.info("Loading other offices data...");
        OtherOfficesWrapperDto wrapper = objectMapper.readValue(OTHER_OFFICES_JSON, OtherOfficesWrapperDto.class);
        processCategories(wrapper.getOtherOffices(), "OtherOffice");
    }

    private void processCategories(List<CategoryDataDto> categoryDataList, String type) {
        if (categoryDataList == null) {
            logger.warn("Category data list for type '{}' is null. Skipping.", type);
            return;
        }

        for (CategoryDataDto categoryDto : categoryDataList) {
            Category category = new Category();
            category.setCategoryId(categoryDto.getId());
            category.setCategoryName(categoryDto.getName());
            category.setColor(categoryDto.getColor());
            category.setCategoryType(type);
            // Note: Category's 'sections' list will be populated by JPA relationship mapping,
            // so we don't set it directly here.
            categoryRepository.save(category);
            logger.debug("Saved category: {} ({})", category.getCategoryName(), category.getCategoryId());

            if (categoryDto.getSections() != null) {
                for (SectionDto sectionDto : categoryDto.getSections()) {
                    Section section = new Section();
                    section.setSectionId(sectionDto.getId());
                    section.setSectionName(sectionDto.getName());
                    section.setCategory(category); // Link to parent category
                    sectionRepository.save(section);
                    logger.debug("  Saved section: {} ({}) under category {}", section.getSectionName(), section.getSectionId(), category.getCategoryId());

                    if (sectionDto.getContacts() != null) {
                        for (ContactDto contactDto : sectionDto.getContacts()) {
                            Contact contact = new Contact();
                            contact.setDesignation(contactDto.getDesignation());
                            contact.setName(contactDto.getName());
                            contact.setCallSign(contactDto.getCallSign());
                            contact.setLandline(contactDto.getLandline());
                            contact.setContact1(contactDto.getContact1());
                            contact.setContact2(contactDto.getContact2());
                            contact.setEmail(contactDto.getEmail());
                            contact.setDetails(contactDto.getDetails());
                            contact.setSection(section); // Link to parent section
                            contactRepository.save(contact);
                            logger.trace("    Saved contact: {} for section {}", contact.getName(), section.getSectionId());
                        }
                    } else {
                        logger.debug("    No contacts for section: {} ({})", section.getSectionName(), section.getSectionId());
                    }
                }
            } else {
                logger.debug("  No sections for category: {} ({})", category.getCategoryName(), category.getCategoryId());
            }
        }
    }
}
