my_lambda = -> { return 1 }
puts my_lambda.call

strings = %w[leonardo donatello raphael michaelangelo]
symbolize = ->(x) { x.intern }
symbols = strings.collect(&symbolize)
puts symbols

strings = %w[leonardo donatello raphael michaelangelo]
symbolize = lambda do |x|
  x.intern
end
symbols = strings.collect(&symbolize)
puts symbols

my_array = ['raindrops', :kettles, 'whiskers', :mittens, :packages]
symbol_filter = ->(x) { x.is_a? Symbol }
symbols = my_array.select(&symbol_filter)
puts symbols
