
REPORTER = spec

all: index.js

test:
	@NODE_ENV=. mocha \
		--reporter $(REPORTER) \

test-cov: lib-cov
	SUPERAGENT_COV=1 $(MAKE) test REPORTER=html-cov > coverage.html

docs: test-docs

test-docs:
	make test REPORTER=doc > test.html

.PHONY: test-cov test docs test-docs
