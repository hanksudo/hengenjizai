module ModuleExample
  def foo
    'foo'
  end
end

# Including modules binds their methods to the class instances.
# Extending modules binds their methods to the class itself.
class Person
  include ModuleExample
end

class Book
  extend ModuleExample
end

# Person.foo     #=> NoMethodError: undefined method `foo' for Person:Class
Person.new.foo #=> "foo"
Book.foo       #=> "foo"
# Book.new.foo   #=> NoMethodError: undefined method `foo'
