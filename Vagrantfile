VAGRANTFILE_API_VERSION = '2'

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = 'puppetlabs/centos-7.0-64-puppet'
  config.vm.network 'forwarded_port', guest: 80, host: 8081
  config.ssh.forward_agent = true

  config.vm.provision :shell, path: 'scripts/vagrant-bootstrap'

  config.vm.provider 'virtualbox' do |vb|
    vb.customize ['modifyvm', :id, '--memory', '2048']
  end
end
