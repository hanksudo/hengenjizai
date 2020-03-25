class Person
  attr_accessor :name, :age :instance_var
  def initialize(name, age)
    @name = name
    @age = age
    @instance_var
  end
end

class Superman < Person
  def initialize
    super
  end
end

p = Person.new('hank', 35)
puts p.name
puts p.age
