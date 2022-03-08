Feature: Booking a hotel using Demo site

    Scenario: Book a hotel
        When I open the demo site
        Then Select the desired location 
        And Select the number of guests
        Then Search for the results
        Then Select one of the hotels
        And Proceed with booking
        Then Download the invoice
        
