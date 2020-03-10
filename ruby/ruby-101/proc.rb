multiples_of_3 = proc do |n|
  (n % 3).zero?
end

print (1..100).to_a.select(&multiples_of_3)
puts

def greeter
  yield
end

phrase = proc { puts 'Hello there!' }

greeter(&phrase)
phrase.call
