# AWS API Models

> [!WARNING]
> This repository is not closely monitored, and the files here are generated from other sources. Because
> of this, we are unable to accept pull requests and issues related to the service models should be directed to other
> channels. 
> 
> For questions related to the AWS SDKs, please visit the respective repository for
> [SDK specific support](https://github.com/aws). If you have an AWS support plan, you can create a new technical
> support case in the [AWS Support Center](https://console.aws.amazon.com/support/home#/). Alternatively, you may
> reach out to general AWS Support [here](https://aws.amazon.com/contact-us/)).

This repository contains [Smithy](https://smithy.io/) models (in the
[JSON AST](https://smithy.io/2.0/spec/json-ast.html) form) for all public AWS API services. Smithy is an open-source
interface definition language, set of code generators, and developer tools used to generate code for web services and 
client SDKs from API models. At AWS, we use Smithy extensively to model our service APIs and provide the daily releases
of the AWS SDKs and CLIs.

AWS API models can be helpful for a variety of uses cases such as building custom SDKs/CLIs SDKs for use with AWS or
implementing MCP servers to interact with AWS services.


## Directory Structure

The AWS models repository contains a top-level directory, `models`, which contains:

* One directory per public AWS service
    * Note: service directories are named using the `<sdk-id>` of the service, where `<sdk-id>` is the value of the
    model's [`sdkId`](https://smithy.io/2.0/aws/aws-core.html#sdkid), lowercased and with spaces converted to hyphens
* Each service directory contains one directory per `<version>` of the service, where `<version>` is the value of the
service shape's [version property](https://smithy.io/2.0/spec/service-types.html#service)
* Contained within a service-version directory, a model file named `<sdk-id>-<version>.json` will be present


## Learn more

We invite you to learn more about the AWS preferred API modeling language at [smithy.io](https://smithy.io/).


## License

This project is licensed under the [Apache-2.0 License](LICENSE). 

