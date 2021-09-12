from tkinter import *

root = Tk()
root.title("Tic Tac Toe")
welcome = "Welcome to Tic Tac Toe! \nPlay with a friend to get three in a row!"
hello = Label(text=welcome)
hello.grid(row=0, column=0, columnspan=3, padx=10, pady=10)

turn = True
count = 0

def button_choice(number):
    global turn, count
    #X's Turn
    if number["text"] == " " and turn == True:
        number["text"] = "X"
        number["bg"] = "red"
        turn = False
        count += 1
        if count >= 5:
            find_winner()
            #Annouce tie if board is filled and no winner text is displayed
            if count == 9 and hello["text"] == welcome:
                hello["text"] = "It's a tie!"
    #O's Turn
    elif number["text"] == " " and turn == False:
        number["text"] = "O"
        number["bg"] = "green"
        turn = True
        count += 1
        if count >= 5:
            find_winner()
            #Annouce tie if board is filled and no winner text is displayed
            if count == 9 and hello["text"] == welcome:
                hello["text"] = "It's a tie!"

#Disable buttons after a winner is declared
def disable_buttons ():
    button_1.configure(state=DISABLED)
    button_2.configure(state=DISABLED)
    button_3.configure(state=DISABLED)
    button_4.configure(state=DISABLED)
    button_5.configure(state=DISABLED)
    button_6.configure(state=DISABLED)
    button_7.configure(state=DISABLED)
    button_8.configure(state=DISABLED)
    button_9.configure(state=DISABLED)

def find_winner():
    winner = " "
    #Check Rows
    if button_1["text"] == button_2["text"] and button_2["text"] == button_3["text"]:
        winner = button_1["text"]
    elif button_4["text"] == button_5["text"] and button_5["text"] == button_6["text"]:
        winner = button_4["text"]
    elif button_7["text"] == button_8["text"] and button_8["text"] == button_9["text"]:
        winner = button_7["text"]
    #Check Columns
    elif button_1["text"] == button_4["text"] and button_4["text"] == button_7["text"]:
        winner = button_1["text"]
    elif button_2["text"] == button_5["text"] and button_5["text"] == button_8["text"]:
        winner = button_2["text"]
    elif button_3["text"] == button_6["text"] and button_6["text"] == button_9["text"]:
        winner = button_3["text"]
    #Check Diagonals
    elif button_1["text"] == button_5["text"] and button_5["text"] == button_9["text"]:
        winner = button_1["text"]
    elif button_3["text"] == button_5["text"] and button_5["text"] == button_7["text"]:
        winner = button_3["text"]
    #No winner yet
    else:
        pass
    
    #Check if it's blank or an actual win
    if winner == " ":
        pass
    else:
        hello["text"] = "Congrats! " + winner + " won!"
        disable_buttons()

#Define buttons
button_1 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_1))
button_2 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_2))
button_3 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_3))
button_4 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_4))
button_5 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_5))
button_6 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_6))
button_7 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_7))
button_8 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_8))
button_9 = Button(root, text=" ", padx="40", pady="20", command=lambda:button_choice(button_9))

#Put the buttons on the screen
button_1.grid(row=1, column=0)
button_2.grid(row=1, column=1)
button_3.grid(row=1, column=2)

button_4.grid(row=2, column=0)
button_5.grid(row=2, column=1)
button_6.grid(row=2, column=2)

button_7.grid(row=3, column=0)
button_8.grid(row=3, column=1)
button_9.grid(row=3, column=2)

root.mainloop()
