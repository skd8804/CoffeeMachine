import kotlin.system.exitProcess

enum class Action (){
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT
}

enum class Coffee(val number: Int, val water: Int, val beans: Int, val milk: Int, val cups: Int, val cost: Int){
    ESPRESSO(1, 250, 16, 0, 1, 4),
    LATTE(2, 350, 20, 75, 1, 7),
    CAPPUCINO(3, 200, 12, 100, 1, 6),
    NULL(4, 0, 0 , 0 , 0, 0);

    companion object {
        fun selectCoffee(number: Int): Coffee{
            for(enum in Coffee.values()) {
                if (enum.number == number) return enum
            }
            return NULL
        }
    }
}

class CoffeeMachine(
    var water: Int = 400,
    var beans: Int = 120,
    var milk: Int = 540,
    var cups: Int = 9,
    var money: Int = 550
)

{
    //  check which action
    fun action(action: String) {
//        Action.valueOf(action).toString().lowercase()

        when (action) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
            "remaining" -> remaining()
            "exit" -> end()
        }

    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back:")
        val input = readln()
        if (input == "back") return

        val drinkChoice = Coffee.selectCoffee(input.toInt())
        if (drinkChoice.water > water) {
            println("Sorry, not enough water!")
        } else if (drinkChoice.beans > beans) {
            println("Sorry, not enough coffee beans!")
        } else if (drinkChoice.milk > milk) {
            println("Sorry, not enough coffee milk!")
        } else if (drinkChoice.cups > cups) {
            println("Sorry, not enough disposable cups!")
        } else {
            println("I have enough resources, making you a coffee!")
            water -= drinkChoice.water
            beans -= drinkChoice.beans
            milk -= drinkChoice.milk
            cups -= drinkChoice.cups
            money += drinkChoice.cost
        }
    }

    private fun fill() {
        println("Write how many ml of water do you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk do you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans do you want to add:")
        beans += readln().toInt()
        println("Write how many disposable cups of coffee do you want to add:")
        cups += readln().toInt()
    }

    private fun take() {
        println("I gave you \$$money")
        money -= money
    }

    private fun remaining() {
        println(
            "The coffee machine has:\n" +
                    "$water ml of water\n" +
                    "$milk ml of milk\n" +
                    "$beans g of coffee beans\n" +
                    "$cups disposable cups\n" +
                    "\$$money of money"
        )
    }

    private fun end() {
        exitProcess(1)
    }
}



fun main() {
//  creates coffee machine
    val coffeeMachine = CoffeeMachine()
    while (true) {

        println("Write action (buy, fill, take, remaining, exit):")
        val action = readln().lowercase()
        coffeeMachine.action(action)


    }
}