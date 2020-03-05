puts 'Guest the number!'

SECRET_NUMBER = rand(1..100)
# puts "The secret number is #{SECRET_NUMBER}"

loop do
  guess = gets.chomp.to_i
  puts "Your guessed: #{guess}"
  if guess > SECRET_NUMBER
    puts 'Too big!'
  elsif guess < SECRET_NUMBER
    puts 'Too small!'
  else
    puts 'You win!'
    break
  end
end
