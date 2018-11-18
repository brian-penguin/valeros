# Valeros

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Local Setup

- You will need to have postgres running
- You will need to add a dev-config.edn and a test-config.edn file to the path route, You can copy the sample over.
- Create a database for both dev and test envs (this assumes your user can create databases and has access to the `createdb` command)
    ```
    $ createdb valeros_dev
    $ createdb valeros_test
    ```
- run  `lein run migrate` to get the migrations required (this will work for both test and dev)

Everything should be setup now and you can start the app with lein run


## Running

To start a web server for the application, run:

    lein run

## License

generated using Luminus version "3.10.14"
Copyright © 2018 @me
