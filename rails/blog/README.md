# README

This README would normally document whatever steps are necessary to get the
application up and running.

Things you may want to cover:

* Ruby version

* System dependencies

* Configuration

* Database creation

* Database initialization

* How to run the test suite

* Services (job queues, cache servers, search engines, etc.)

* Deployment instructions

* ...

## Command

```bash
bin/rails server

# create controller
bin/rails generate controller Articles index --skip-routes

# create model
bin/rails generate model Article title:string body:text
bin/rails db:migrate

bin/rails console

> article = Article.new(title: "Hello Rails", body: "I am on Rails!")
> article.save
> article
> Article.find(1)
> Article.all

```
