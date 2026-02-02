#
# Resource definitions
#

data "aws_ami" "apache" {
  filter {
    name   = "name"
    values = [var.ami_name]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  owners = [var.ami_owner]

  most_recent = true
}

resource "aws_instance" "web" {
  ami           = data.aws_ami.apache.id
  instance_type = "t2.micro"
  subnet_id     = aws_subnet.frontend.id
  tags = {
    manny     = "cepeda"
    yor_trace = "24e327d4-d7c5-478d-9959-596925dfd985"
  }
}
resource "aws_subnet" "frontend" {
  vpc_id     = aws_vpc.apps.id
  cidr_block = "10.0.1.0/24"
  tags = {
    manny     = "cepeda"
    yor_trace = "aa41baee-44e7-441e-b286-f7c02dc8b0fd"
  }
}

resource "aws_vpc" "apps" {
  cidr_block = "10.0.0.0/16"
  tags = {
    manny     = "cepeda"
    yor_trace = "d4929aec-8a98-465b-8e4e-e8038f0cb7cc"
  }
}
