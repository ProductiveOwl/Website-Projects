import random

def rules():
  print("Here are the rules:")
  print("You have to guess the word that is hidden with the asterisks.")
  print("Guess letters one at a time to figure out the word.")
  print("You win when you guess the word in the number of attempts you've selected.")

def check_user_input (letters_guessed):
  x = False
  while x == False:
    user_guess = input("Your guess: ")
    user_guess = user_guess.strip()
    user_guess = user_guess.upper()

    if user_guess in letters_guessed:
      print ("You already guessed that letter.")
    elif user_guess.isalpha() == False:
      print ("Please use a valid English letter.")
    elif len(user_guess) > 1 or len(user_guess) < 1:
      print("Please type in 1 letter.")
    else:
      x = True
  return user_guess

#Check if the input number is within the range of options available
def check_num_choice(min, max, check_num):
  #check_num must be a string
  check_num = check_num.strip()
  if check_num.isdigit():
    integer = int(check_num)
    if integer <= max and integer >= min:
      return check_num
    else:
      print("Pick a number between " + str(min) + " and " + str(max) + ".")
      return False
  else:
    print("Pick a valid number.")
    return False

def choose_word():
  #All words need to be uppercase
  animals = ["CAT", "DOG", "ELEPHANT", "LION", "FROG", "ROBIN"]
  food = ["BURGER", "FRIES", "ICE CREAM", "SPAGHETTI", "PASTA", "SOUP"]
  drinks = ["MILKSHAKE", "LEMONADE", "WATER", "JUICE", "COFFEE", "TEA"]
  electronics = ["COMPUTER", "LAPTOP", "TABLET", "PHONE", "HEADPHONES", "TELEVISION"]
  stationary = ["PENCIL", "ERASER", "SCISSORS", "GLUE STICK", "STAPLER"]
  school_subjects = ["GYM", "HISTORY", "MATH", "SCIENCE", "LANGUAGE", "GEOGRAPHY"]
  categories = {1: animals, 2: food, 3: drinks, 4: electronics, 5: stationary, 6: school_subjects}
  #Check if it's a valid input
  valid_input = False
  while valid_input == False:
    cat_choice = input("""Choose a category: 
                          1 = Animals
                          2 = Food 
                          3 = Drinks
                          4 = Electronics
                          5 = Stationary
                          6 = School Subjects
                        Your choice: """)
    valid_input = check_num_choice(1, 6, cat_choice)
  #Choose a word from the category
  chosen_category = categories[int(cat_choice)]
  word = random.choice(chosen_category)
  return word

def select_difficulty (word_to_guess):
  difficulty = {1: 100, 2: len(word_to_guess) + 10, 3: len(word_to_guess) + 5, 4: len(word_to_guess) + 2}
  #Check if user inputs a valid number
  valid_input = False
  while valid_input == False:
    diff_choice = input("""Select a difficulty:
              1 = No challenge (Guess until word is complete)
              2 = Easy (Allows 10 wrong answers)
              3 = Medium (Allows 5 wrong answers)
              4 = Hard (Allows 2 wrong answers)
              Your choice: """)
    valid_input = check_num_choice(1, 4, diff_choice)
  #Get number of tries from the dictionary
  max_tries = difficulty[int(diff_choice)]
  return max_tries

#Start game
print ("Welcome to Hangman!")
display_rules = input("Would you like to know the rules?\n(press \"y\" for yes or any button to skip): ")
if display_rules == "y" or display_rules == "Y":
  rules()

play_again = "y"
while play_again == "y" or play_again == "Y":
  #Select a word and define variables
  word_to_guess = choose_word()
  word_displayed = "*" * len(word_to_guess)
  letters_left = list(word_to_guess)
  li_displayed_word = list(word_displayed)
  ans_word = word_to_guess
  attempts = 1 
  letters_guessed = []

  #Choose difficulty
  max_tries = select_difficulty(word_to_guess)

  #Place spaces in the right places
  if " " in word_to_guess:
      letter_occurences = word_to_guess.count(" ")
      for i in range(letter_occurences):
        #Find occurences
        index_of_letter = ans_word.find(" ")
        #Reveal letter
        li_displayed_word[index_of_letter] = " "
        #Delete from original
        letters_left[index_of_letter] = "*"
        ans_word = "".join(letters_left)
        word_displayed = "".join(li_displayed_word)

  while attempts <= max_tries and word_displayed.find("*") != -1:
    print ("\nAttempt: " + str(attempts) + " (" + str(max_tries - attempts) + " left)")
    print("Letters guessed: " + str(letters_guessed))
    print ("Here is your word: " + word_displayed)
    guess_letter = check_user_input(letters_guessed)
    letters_guessed.append(guess_letter)

    if guess_letter in word_to_guess:
      print ("That letter is in the word!")
      #Count the number of times the letter appears
      letter_occurences = word_to_guess.count(guess_letter)
      #Replace the letters
      for i in range(letter_occurences):
        #Find occurences
        index_of_letter = ans_word.find(guess_letter)
        #Reveal letter
        li_displayed_word[index_of_letter] = guess_letter
        #Delete from original
        letters_left[index_of_letter] = "*"
        ans_word = "".join(letters_left)
        word_displayed = "".join(li_displayed_word)

    else:
      print("That letter is not in the word.\n")
    attempts += 1

  #Display message about the outcome
  if word_displayed.find("*") == -1:
    print ("\nYou win! The word is " + word_displayed)
  else:
    print ("\nYou lost. The word is " + word_to_guess)

  play_again = input("Would you like to play again? (y for yes and any other key to end): ")
print ("Thanks for playing!")
