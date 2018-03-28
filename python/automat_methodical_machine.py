# https://github.com/glyph/automat
from automat import MethodicalMachine

class CoffeeMachine(object):
    _machine = MethodicalMachine()

    @_machine.input()
    def brew_button(self):
        "The user pressed the 'brew' button."

    @_machine.input()
    def put_in_beans(self, beans):
        "The user put in some beans."

    @_machine.output()
    def _save_beans(self, beans):
        "The beans are now in the machine; save them."
        print "Called _save_beans():", beans
        self._beans = beans

    @_machine.output()
    def _describe_coffee(self):
        return "A cup of coffee made with {}".format(self._beans)

    @_machine.output()
    def _heat_the_heating_element(self):
        "Heat up the heating element, which should cause coffee to happen."
        print "Called _heat_the_heating_element()"
        # self._heating_element.turn_on()

    @_machine.state()
    def have_beans(self):
        "In this state, you have some beans."

    # dont_have_beans is the initial state because CoffeeBrewer starts without beans in it.
    @_machine.state(initial=True)
    def dont_have_beans(self):
        "In this state, you don't have any beans."

    # When we don't have beans, upon putting in beans, we will then have beans
    # (and produce no output)
    dont_have_beans.upon(put_in_beans, enter=have_beans, outputs=[_save_beans])

    # When we have beans, upon pressing the brew button, we will then not have
    # beans any more (as they have been entered into the brewing chamber) and
    # our output will be heating the heating element.
    have_beans.upon(brew_button, enter=dont_have_beans,
                    outputs=[_heat_the_heating_element,
                             _describe_coffee],
                    collector=lambda iterable: list(iterable)[-1]
    )


def main():
    coffee_machine = CoffeeMachine()
    coffee_machine.put_in_beans("real good beans")
    print coffee_machine.brew_button()

if __name__ == '__main__':
    main()