books = [
  'Charlie and the Chocolate Factory',
  'War and Peace', 'Utopia',
  'A Brief History of Time',
  'A Wrinkle in Time'
]

# ascending order
books.sort! { |first_book, second_book| first_book <=> second_book }
print books
puts

# descending order
books.sort! { |first_book, second_book| second_book <=> first_book }
print books
