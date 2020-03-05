puts 'What\'s your first name? '
first_name = gets.chomp
first_name.capitalize!

puts 'What\'s your last name? '
last_name = gets.chomp
last_name.capitalize!

puts 'Which city do you live? '
city = gets.chomp
city.capitalize!

puts 'Which state do you live? '
state = gets.chomp
state.upcase!
puts "#{first_name} #{last_name} live in #{city} #{state}"
