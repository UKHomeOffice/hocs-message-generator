# hocs-message-generator
A tool that creates (test) messages to be added to the hocs-case-creator inbound SQS queue.

## JSON Schema

The service uses the JSON schema defined
in [hocs-ukvi-complaint-schema](https://github.com/UKHomeOffice/hocs-ukvi-complaint-schema). The schema is pulled into
the build as a dependency in the gradle file.

For local development, build the schema locally and publish
to a Maven local repository. View the README in `hocs-ukvi-complaint-schema` for more information.

