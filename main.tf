terraform {
  backend "s3" {
    endpoint = "http://my-minio-service:9000"
    key = "terraform.tfstate"
    region = "main"
    access_key = "minio"
    secret_key = "miniostorage"
    bucket = "terraform"
    skip_requesting_account_id = true
    skip_credentials_validation = true
    skip_get_ec2_platforms = true
    skip_metadata_api_check = true
    skip_region_validation = true
    force_path_style = true
  }
}

resource "null_resource" "test" {
}