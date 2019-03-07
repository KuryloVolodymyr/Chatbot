Feature: marvel
  Scenario: pass
    When i write "hello"
    Then i get "Sorry, but I can't see detect hero name in your messages, could your messages similar to "Who is Ant-Man?""

#    Scenario: fail
#    When i write "kurylo"
#    Then i get "kurylo"
#

  Scenario: qr
    When i click quick reply with title "Hulk" and  payload "hero"
    Then i get template with title "Hulk" and "Read more" button and "Comics" button and "Rate" button


