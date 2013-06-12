
// Host Configurations

hostConfigs {
	localhost {
		user = 'myuser'
		password = 'mypass'
		perfLoadHome = '..' // relative path
		osfamily = 'windows' // or 'unix'
		client = true
		perfmon = true
		//startup = ['cmd.exe /c "cd ../refapp && refapp.cmd"']
		//shutdown = ['cd refapp && ./shutdown.sh']
		archiving {
			gcLogs {
				dir = '../refapp'
				files = 'gclog.txt'
				zipName = 'gc-logs.zip'
				cleanup = false
			}
			agentLogs {
				dir = '../agent'
				files = '*.log'
				zipName = 'agent-logs.zip'
				cleanup = false
			}
		}
	}
}